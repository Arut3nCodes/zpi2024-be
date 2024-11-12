package com.zpi.fryzland.service;

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
    List<ServicesIncludedInTheVisitModel> saveAllServiceVisitConnections(List<ServiceModel> serviceModelList, VisitModel visitModel){
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

    List<ServicesIncludedInTheVisitModel> getAllConnectionsByVisitId(int visitID){
        return repository.getAllByVisitModel_VisitID(visitID);
    }
}
