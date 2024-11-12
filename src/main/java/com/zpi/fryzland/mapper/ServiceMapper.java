package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.ServiceDTO;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.ServiceCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ServiceMapper implements Mapper<ServiceModel, ServiceDTO>{
    private final ServiceCategoryService categoryService;

    @Override
    public ServiceModel toModel(ServiceDTO dto, boolean withId) {
        Optional<ServiceCategoryModel> categoryModel = categoryService.getCategoryById(dto.getServiceCategoryID());
        if(categoryModel.isPresent()){
            return new ServiceModel(
                    withId ? dto.getServiceID() : null,
                    dto.getServiceName(),
                    dto.getServiceSpan(),
                    dto.getServicePrice(),
                    dto.getServiceDescription(),
                    categoryModel.get()
            );
        }
        return null;
    }

    @Override
    public ServiceDTO toDTO(ServiceModel model) {
        return new ServiceDTO(
                model.getServiceID(),
                model.getServiceName(),
                model.getServiceSpan(),
                model.getServicePrice(),
                model.getServiceDescription(),
                model.getServiceCategoryModel() != null ? model.getServiceCategoryModel().getServiceCategoryId() : null
        );
    }
}
