package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Data
public class RescheduleDTO {
    private final Integer userID;
    private char userRole = 'P';
    private final LocalDate rescheduleDate;
    private final LocalTime rescheduleTime;
}
