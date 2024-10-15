package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitDTO {
    private Integer visitID;
    private Date visitDate;
    private Time visitStartDate;
    private Integer assigmentID;  // Replaced AssigmentToSalonModel with an Integer ID
    private Integer customerID;    // Replaced CustomerModel with an Integer ID
}
