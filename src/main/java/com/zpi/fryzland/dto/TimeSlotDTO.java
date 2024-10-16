package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeSlotDTO {
    private Date timeSlotDate;
    private Time timeSlotTime;
    private Integer employeeID;  // Replaced EmployeeModel with an Integer ID
}
