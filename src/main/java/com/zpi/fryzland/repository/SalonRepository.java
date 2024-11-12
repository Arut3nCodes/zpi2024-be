package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.SalonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<SalonModel, Integer> {
}
