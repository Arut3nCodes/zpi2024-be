package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServicesIncludedInTheVisitDTO {
    private Integer serviceInVisitId;
    private Integer serviceID;    // Replaced ServiceModel with an Integer ID
    private Integer visitID;      // Replaced VisitModel with an Integer ID
}
