package com.zpi.fryzland.repository;

import com.zpi.fryzland.model.ServicesIncludedInTheVisitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesIncludedInTheVisitRepository extends JpaRepository<ServicesIncludedInTheVisitModel, Integer> {
    public List<ServicesIncludedInTheVisitModel> getAllByVisitModel_VisitID(int visitID);

}
