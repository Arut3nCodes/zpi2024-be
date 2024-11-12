package com.zpi.fryzland.dto.serviceDisplay;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithServicesDTO {
    List<CategoryDTO> listOfCategories;
}
