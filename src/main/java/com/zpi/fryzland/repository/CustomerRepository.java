package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    Optional<CustomerModel> findByCustomerEmail(String customerEmail);
    List<CustomerModel> findAllByServiceCategoryModel(ServiceCategoryModel serviceCategoryModel);
}
