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
public class TimeSlotDTO {
    private LocalDate timeSlotDate;
    private LocalTime timeSlotTime;
    private Integer employeeID;  // Replaced EmployeeModel with an Integer ID
}
