package com.zpi.fryzland.dto;

import com.zpi.fryzland.model.VisitModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class VisitWithIdsDTO {
    private Integer visitID;
    private LocalDate visitDate;
    private LocalTime visitStartTime;
    private String visitStatus;
    private Integer salonID;
    private Integer employeeID;
    private Integer customerID;

    public VisitWithIdsDTO(VisitModel visitModel){
        this.visitID = visitModel.getVisitID();
        this.visitDate = visitModel.getVisitDate();
        this.visitStartTime = visitModel.getVisitStartTime();
        this.visitStatus = visitModel.getVisitStatus().getValue();
        this.salonID = visitModel.getAssigmentModel().getSalonModel().getSalonID();
        this.employeeID = visitModel.getAssigmentModel().getEmployeeModel().getEmployeeID();
        this.customerID = visitModel.getCustomerModel().getCustomerID();
    }
}
