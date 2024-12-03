package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssigmentToSalonDTO {
    private Integer assigmentID;
    private LocalDate assigmentDate;
    private Integer salonID;
    private Integer employeeID;
}
