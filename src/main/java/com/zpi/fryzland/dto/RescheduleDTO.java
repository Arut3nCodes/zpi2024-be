package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class RescheduleDTO {
    private final LocalDate rescheduleDate;
    private final LocalTime rescheduleTime;
}
