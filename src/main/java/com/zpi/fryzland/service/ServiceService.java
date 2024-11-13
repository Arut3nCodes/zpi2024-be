package com.zpi.fryzland.service;

import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.repository.ServiceCategoryRepository;
import com.zpi.fryzland.repository.ServiceRepository;
import com.zpi.fryzland.model.ServiceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    public ServiceModel addService(ServiceModel serviceModel){
        return serviceRepository.save(serviceModel);
    }

    public Optional<ServiceModel> getServiceById(int id){
        return serviceRepository.findById(id);
    }

    public List<ServiceModel> getAllServices(){
        List<ServiceModel> listOfServices = new ArrayList<>();
        for(ServiceModel serviceModel: serviceRepository.findAll()){
            listOfServices.add(serviceModel);
        }
        return listOfServices;
    }

    public List<ServiceModel> getAllServicesByCategory(ServiceCategoryModel categoryModel){
        List<ServiceModel> listOfServices = new ArrayList<>();
        for(ServiceModel serviceModel: serviceRepository.findAllByServiceCategoryModel(categoryModel)){
            listOfServices.add(serviceModel);
        }
        return listOfServices;
    }

    public List<ServiceModel> getAllServicesByIds(List<Integer> listOfIds){
        return serviceRepository.findAllById(listOfIds);
    }

    public void deleteService(ServiceModel serviceModel){
        serviceRepository.delete(serviceModel);
    }

    public void deleteServiceById(int id){
        serviceRepository.deleteById(id);
    }
}
