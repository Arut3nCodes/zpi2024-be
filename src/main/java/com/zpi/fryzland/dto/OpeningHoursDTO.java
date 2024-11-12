package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OpeningHoursDTO {
    private Integer openingHoursID;
    private Integer weekday;
    private Time openingHour;
    private Time closingHour;
    private Integer salonID;
}
