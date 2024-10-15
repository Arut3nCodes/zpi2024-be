package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceCategoryDTO {
    private Integer serviceCategoryId;
    private String categoryName;
    private String categoryDescription;
}
