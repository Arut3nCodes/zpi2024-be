package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalonDTO {
    private Integer salonID;
    private String salonName;
    private String salonDialNumber;
    private String salonCity;
    private String salonStreet;
    private String salonBuildingNumber;
    private String salonPostalCode;
}
