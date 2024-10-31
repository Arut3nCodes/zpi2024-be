package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.VisitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends JpaRepository<VisitModel, Integer> {
}
