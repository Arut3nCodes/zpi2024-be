package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveVisitDTO {
    private Date visitDate;
    private Time visitStartDate;
    private Integer SalonID;
    private Integer employeeID;
    private Integer customerID;
    private List<Integer> serviceIDList;
}
