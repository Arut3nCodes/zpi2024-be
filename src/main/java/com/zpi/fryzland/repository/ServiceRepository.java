package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.SalonModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.ServiceCategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceModel, Integer> {
    Iterable<ServiceModel> findAllByServiceCategoryModel(ServiceCategoryModel serviceCategoryModel);    
}
