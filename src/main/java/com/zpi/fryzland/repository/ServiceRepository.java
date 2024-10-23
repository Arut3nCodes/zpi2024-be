package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.ServiceModel;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceModel, Integer> {
}
