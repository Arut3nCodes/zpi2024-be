package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.ServicesIncludedInTheVisitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesIncludedInTheVisitRepository extends JpaRepository<ServicesIncludedInTheVisitModel, Integer> {
}
