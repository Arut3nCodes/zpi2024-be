package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveVisitDTO {
    private Integer salonID;
    private Integer employeeID;
    private Integer customerID;
    private List<Integer> serviceIDList;
    private LocalDate visitDate;
    private LocalTime visitStartTime;
}
