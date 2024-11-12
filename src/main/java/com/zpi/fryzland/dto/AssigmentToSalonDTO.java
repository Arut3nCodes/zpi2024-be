package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssigmentToSalonDTO {
    private Integer assigmentID;
    private Date assigmentDate;
    private Integer salonID;
    private Integer employeeID;
}
