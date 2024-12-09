package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.authmodel.CustomerRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequestModel, String> {
    Optional<CustomerRequestModel> findCustomerRequestModelByCustomerModel_CustomerEmail(String customerEmail);
}
