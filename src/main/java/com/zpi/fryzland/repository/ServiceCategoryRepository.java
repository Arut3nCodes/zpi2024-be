package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.ServiceCategoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceCategoryRepository extends CrudRepository<ServiceCategoryModel, Integer> {
}
