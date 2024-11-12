package com.zpi.fryzland.dto.serviceDisplay;

import com.zpi.fryzland.model.ServiceModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer serviceCategoryId;
    private String categoryName;
    private String categoryDescription;
    private List<ServiceModel> listOfServices;
}
