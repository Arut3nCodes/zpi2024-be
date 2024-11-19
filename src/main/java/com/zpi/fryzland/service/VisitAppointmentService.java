package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.SaveVisitDTO;
import com.zpi.fryzland.dto.TimeSlotDTO;
import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.mapper.CategoriesWithServicesMapper;
import com.zpi.fryzland.mapper.SalonMapper;
import com.zpi.fryzland.mapper.TimeSlotMapper;
import com.zpi.fryzland.mapper.VisitMapper;
import com.zpi.fryzland.model.*;
import com.zpi.fryzland.model.enums.VisitStatus;
import com.zpi.fryzland.validators.OpeningHours;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VisitAppointmentService {
    private final SalonService salonService;
    private final AssignmentToSalonService assignmentService;
    private final EmployeeService employeeService;
    private final EmployeeQualificationService employeeQualificationService;
    private final ServiceCategoryService categoryService;
    private final ServiceService serviceService;
    private final CategoriesWithServicesMapper categoriesWithServicesMapper;
    private final OpeningHoursService openingHoursService;
    private final TimeSlotService timeSlotService;
    private final CustomerService customerService;
    private final VisitService visitService;
    private final ServicesIncludedInTheVisitService serviceIncludedService;
    private final TimeSlotMapper timeSlotMapper;

    public CategoryWithServicesDTO getAllCategoriesWithServicesInTheTimespan(int salonId){
        List<AssignmentToSalonModel> listOfAssignments = assignmentService.findAllAssignmentsBySalonID(salonId);
        List<EmployeeModel> listOfEmployees = employeeService.getAllEmployeesByAssignment(listOfAssignments);
        List<EmployeeQualificationModel> listOfQualifications = employeeQualificationService.findAllQualificationsByEmployee(listOfEmployees);
        List<ServiceCategoryModel> listOfCategories = categoryService.getAllUniqueCategoriesByEmployeeQualification(listOfQualifications);
        return categoriesWithServicesMapper.toDTO(listOfCategories);
    }

    public List<EmployeeModel> getAllEmployeesThatProvideChosenServices(int salonId, List<Integer> serviceIds) {
        List<AssignmentToSalonModel> listOfAssignments = assignmentService.findAllAssignmentsBySalonID(salonId);
        List<EmployeeModel> listOfEmployees = employeeService.getAllEmployeesByAssignment(listOfAssignments);
        List<ServiceModel> listOfServices = serviceService.getAllServicesByIds(serviceIds);
        List<ServiceCategoryModel> listOfCategories = listOfServices.stream()
                .map(model -> model.getServiceCategoryModel())
                .distinct()
                .toList();
        return employeeQualificationService.findAllByEmployeesAndCategories(listOfEmployees, listOfCategories)
                .stream()
                .collect(Collectors.groupingBy(model -> model.getEmployeeModel(), Collectors.mapping(model -> model.getServiceCategoryModel(), Collectors.toList())))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().containsAll(listOfCategories))
                .map(entry -> entry.getKey())
                .toList();
    }
    public List<OpeningHoursModel> getAllOpeningHoursForSalon(int salonId){
        Optional<SalonModel> optionalSalonModel = salonService.getSalonById(salonId);
        if(optionalSalonModel.isPresent()){
            return openingHoursService.getAllOpeningHoursBySalonModel(optionalSalonModel.get());
        }
        else{
            return new ArrayList<>();
        }
    }

    public List<TimeSlotDTO> getAllTimeSlotsForEmployeeBeforeDate(int employeeId, LocalDate date){
        Optional<EmployeeModel> optionalEmployeeModel = employeeService.getEmployeeById(employeeId);
        if(optionalEmployeeModel.isPresent()){
            return timeSlotService.getAllTimeSlotsByEmployeeBeforeDate(optionalEmployeeModel.get(), date)
                    .stream()
                    .map(model -> timeSlotMapper.toDTO(model))
                    .toList();
        }
        else{
            return new ArrayList<>();
        }
    }

    public VisitModel makeAnAppointment(SaveVisitDTO visitDTO){
        AssignmentToSalonModel assignmentModel = assignmentService.findAssignmentByEmployeeAndSalonAndDate(
                visitDTO.getSalonID(), visitDTO.getEmployeeID(), visitDTO.getVisitDate()
        );
        List<ServiceModel> listOfServices = serviceService.getAllServicesByIds(visitDTO.getServiceIDList());
        Optional<CustomerModel> customerModel = customerService.findCustomerById(visitDTO.getCustomerID());

        if(assignmentModel != null && listOfServices.size() == visitDTO.getServiceIDList().size() && customerModel.isPresent()){
            long howManyTimeSlots = listOfServices.stream()
                    .mapToLong(model -> model.getServiceSpan())
                    .sum();
            if(timeSlotService.checkIfNextTimeSlotsSinceTimeExist(assignmentModel.getEmployeeModel(), howManyTimeSlots, visitDTO.getVisitStartTime())){
                timeSlotService.createAndSaveMultipleTimeslots(
                        assignmentModel.getEmployeeModel(),
                        visitDTO.getVisitDate(),
                        visitDTO.getVisitStartTime(),
                        howManyTimeSlots
                );
            }
            else{
                return null;
            }

            VisitModel visitModel = new VisitModel(
                    null,
                    visitDTO.getVisitDate(),
                    visitDTO.getVisitStartTime(),
                    visitDTO.getVisitStatus() != null ? VisitStatus.valueOf(visitDTO.getVisitStatus()) : null,
                    assignmentModel,
                    customerModel.get()
            );

            visitModel = visitService.addVisit(visitModel);
            if(visitModel != null){
                List<ServicesIncludedInTheVisitModel> serviceIncludedList = serviceIncludedService.saveAllServiceVisitConnections(listOfServices, visitModel);
                if(serviceIncludedList.size() == listOfServices.size()){
                    return visitModel;
                }
            }
        }
        return null;
    }

    public List<LocalDate> getAllDatesEmployeesAreAvailableOn(int salonId, int employeeId){
        return assignmentService.getAllAvailabilityDatesForAnEmployee(salonId, employeeId);
    }
}
