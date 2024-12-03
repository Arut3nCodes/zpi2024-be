package com.zpi.fryzland.service;

import com.zpi.fryzland.dto.ServicesIncludedInTheVisitDTO;
import com.zpi.fryzland.mapper.ServicesIncludedInTheVisitMapper;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.model.ServicesIncludedInTheVisitModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.repository.ServicesIncludedInTheVisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicesIncludedInTheVisitService{
    ServicesIncludedInTheVisitRepository repository;
    ServicesIncludedInTheVisitMapper mapper;
    public List<ServicesIncludedInTheVisitModel> saveAllServiceVisitConnections(List<ServiceModel> serviceModelList, VisitModel visitModel){
        List<ServicesIncludedInTheVisitModel> listOfServicesIncluded = new ArrayList<>();
        for(ServiceModel service : serviceModelList){
            ServicesIncludedInTheVisitModel serviceIncluded = new ServicesIncludedInTheVisitModel(
                    null,
                    service,
                    visitModel
            );
            listOfServicesIncluded.add(serviceIncluded);
        }
        return repository.saveAll(listOfServicesIncluded);
    }

    public List<ServicesIncludedInTheVisitModel> getAllConnectionsByVisitId(int visitID){
        return repository.getAllByVisitModel_VisitID(visitID);
    }

    public List<ServicesIncludedInTheVisitDTO> getAllDtoConnectionsByVisitId(int visitID){
        return mapper.allToDTO(
                getAllConnectionsByVisitId(visitID)
        );
    }

    public List<ServicesIncludedInTheVisitModel> getAllConnectionsByCustomerId(int customerID){
        return repository.getAllByVisitModel_CustomerModel_CustomerID(customerID);
    }

    public List<ServicesIncludedInTheVisitDTO> getAllDtoConnectionsByCustomerId(int customerID){
        return mapper.allToDTO(
                getAllConnectionsByCustomerId(customerID)
        );
    }

    public List<ServicesIncludedInTheVisitModel> getAllConnectionsByEmployeeId(int employeeID){
        return repository.getAllByVisitModel_AssigmentModel_EmployeeModel_EmployeeID(employeeID);
    }

    public List<ServicesIncludedInTheVisitDTO> getAllDtoConnectionsByEmployeeId(int employeeID){
        return mapper.allToDTO(
                getAllConnectionsByEmployeeId(employeeID)
        );
    }

    public List<ServiceModel> getAllServicesByVisitModel(VisitModel visitModel) {
        return repository.getAllByVisitModel(visitModel).stream()
                .map(model -> model.getServiceModel())
                .toList();
    }

    public List<ServicesIncludedInTheVisitModel> getAllConnectionsBySalonId(int salonID){
        return repository.getAllByVisitModel_AssigmentModel_SalonModel_SalonID(salonID);
    }

    public List<ServicesIncludedInTheVisitDTO> getAllDtoConnectionsBySalonId(int salonID){
        return mapper.allToDTO(getAllConnectionsBySalonId(salonID));
    }
}
