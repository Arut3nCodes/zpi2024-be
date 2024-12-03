package com.zpi.fryzland.controller;

import com.zpi.fryzland.dto.RescheduleDTO;
import com.zpi.fryzland.dto.SaveVisitDTO;
import com.zpi.fryzland.dto.TimeSlotDTO;
import com.zpi.fryzland.dto.VisitDTO;
import com.zpi.fryzland.dto.employeeDisplay.SalonServiceIdsDTO;
import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.model.*;
import com.zpi.fryzland.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/crud/appointment-making")
public class VisitAppointmentController {
    private final VisitAppointmentService visitAppointmentService;
    private final OpeningHoursService openingHoursService;

    //todo: Rozszerzyć działanie obu metod o filtrowanie ze względu na odstęp czasu (Ten o którym mówimy w założeniach)
    @GetMapping("/services-and-categories/{salonId}")
    public ResponseEntity<CategoryWithServicesDTO> getAllCategoriesWithServices(@PathVariable int salonId){
        try{
            CategoryWithServicesDTO dtoToSend = visitAppointmentService.getAllCategoriesWithServicesInTheTimespan(salonId);
            return ResponseEntity.ok().body(dtoToSend);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<List<EmployeeModel>> getAllEmployeesThatCanServeService(@RequestBody SalonServiceIdsDTO salonServiceIdsDTO){
        try{
            if(salonServiceIdsDTO.getSalonID() == null || salonServiceIdsDTO.getServiceIds() == null){
                throw new NullPointerException("One of the fields is null");
            }
            else if(salonServiceIdsDTO.getServiceIds().isEmpty() || salonServiceIdsDTO.getServiceIds().size() > 3){
                return ResponseEntity.badRequest().build();
            }
            else{
                List<EmployeeModel> listOfEmployees = visitAppointmentService.getAllEmployeesThatProvideChosenServices(salonServiceIdsDTO.getSalonID(), salonServiceIdsDTO.getServiceIds());
                return ResponseEntity.ok(listOfEmployees);
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/opening-hours/{salonId}")
    public ResponseEntity<List<OpeningHoursModel>> getAllOpeningHoursForSalon(@PathVariable int salonId){
        try{
            List<OpeningHoursModel> openingHoursModelList = visitAppointmentService.getAllOpeningHoursForSalon(salonId);
            return ResponseEntity.ok(openingHoursModelList);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/time-slots/{employeeId}")
    public ResponseEntity<List<TimeSlotDTO>> getAllTimeslotsForAnEmployee(@PathVariable int employeeId){
        try{
            List<TimeSlotDTO> timeSlotModelList = visitAppointmentService.getAllTimeSlotsForEmployeeBeforeDate(employeeId, LocalDate.now().plusDays(14));
            return ResponseEntity.ok(timeSlotModelList);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/availability-dates/{salonId}/{employeeId}")
    public ResponseEntity<List<LocalDate>> getAllAvailabilityDatesForEmployee(@PathVariable int salonId, @PathVariable int employeeId){
        try{
            List<LocalDate> dateList = visitAppointmentService.getAllDatesEmployeesAreAvailableOn(salonId, employeeId);
            return ResponseEntity.ok(dateList);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save-visit")
    public ResponseEntity<HttpStatus> saveVisitInDatabase(@RequestBody SaveVisitDTO visitDTO){
        try{
            VisitModel visitModel = visitAppointmentService.makeAnAppointment(visitDTO);
            if(visitModel != null){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/reschedule-visit/{visitId}")
    public ResponseEntity<HttpStatus> rescheduleVisit(@PathVariable int visitId, @RequestBody RescheduleDTO dto) {
        try {
            if ((dto.getUserRole() == 'C' && dto.getUserID() != null) || dto.getUserRole() == 'E') {
                if (visitAppointmentService.rescheduleVisit(dto.getUserRole(), dto.getUserID(), visitId, dto.getRescheduleDate(), dto.getRescheduleTime())) {
                    return ResponseEntity.noContent().build();
                }
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
