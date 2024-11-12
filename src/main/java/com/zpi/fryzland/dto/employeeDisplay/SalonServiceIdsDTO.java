package com.zpi.fryzland.dto.employeeDisplay;

import lombok.Data;

import java.util.List;

@Data
public class SalonServiceIdsDTO {
    private Integer salonID;
    private List<Integer> serviceIds;
}
