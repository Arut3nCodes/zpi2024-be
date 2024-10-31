package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.serviceDisplay.CategoryDTO;
import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.service.ServiceCategoryService;
import com.zpi.fryzland.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CategoriesWithServicesMapper{
    private final ServiceService serviceService;
    public CategoryWithServicesDTO toDTO(List<ServiceCategoryModel> listOfCategories){
        List<CategoryDTO> listOfCategoryDTOs = new ArrayList<>();
        for(ServiceCategoryModel categoryModel : listOfCategories){
           CategoryDTO categoryDTO = new CategoryDTO(
                   categoryModel.getServiceCategoryId(),
                   categoryModel.getCategoryName(),
                   categoryModel.getCategoryDescription(),
                   serviceService.getAllServicesByCategory(categoryModel)
           );
           listOfCategoryDTOs.add(categoryDTO);
        }
        return new CategoryWithServicesDTO(listOfCategoryDTOs);
    }
}
