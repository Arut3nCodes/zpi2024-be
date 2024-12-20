package com.zpi.fryzland.model.compositekey;

import com.zpi.fryzland.model.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeSlotKey implements Serializable {
    private LocalDate timeSlotDate;
    private LocalTime timeSlotTime;
    private Integer employeeModel;
}
