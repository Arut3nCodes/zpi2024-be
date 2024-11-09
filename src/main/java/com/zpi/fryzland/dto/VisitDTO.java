package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitDTO {
    private Integer visitID;
    private LocalDate visitDate;
    private LocalTime visitStartDate;
    private Integer assigmentID;
    private Integer customerID;
}
