package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Integer> {
    Optional<CustomerModel> findByCustomerEmail(String customerEmail);
}
