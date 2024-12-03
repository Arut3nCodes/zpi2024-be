package com.zpi.fryzland.dto;

import com.zpi.fryzland.model.RatingModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RatingDTOWithCustomerID {
    private Integer ratingID;
    private float ratingValue;
    private String ratingOpinion;
    private Integer employeeID;
    private Integer customerID;
    private Integer visitID;

    public RatingDTOWithCustomerID(RatingModel ratingModel){
        this.ratingID = ratingModel.getRatingID();
        this.ratingValue = ratingModel.getRatingValue();
        this.ratingOpinion =  ratingModel.getRatingOpinion();
        this.employeeID = ratingModel.getEmployeeModel().getEmployeeID();
        this.customerID = ratingModel.getVisitModel().getCustomerModel().getCustomerID();
        this.visitID = ratingModel.getVisitModel().getVisitID();
    }
}
