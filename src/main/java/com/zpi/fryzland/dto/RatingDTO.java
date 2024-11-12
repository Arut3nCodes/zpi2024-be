package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDTO {
    private Integer ratingID;
    private float ratingValue;
    private String ratingOpinion;
    private Integer employeeID;
    private Integer visitID;
}
