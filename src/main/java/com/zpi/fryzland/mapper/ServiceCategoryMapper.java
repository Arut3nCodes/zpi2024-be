package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.ServiceCategoryDTO;
import com.zpi.fryzland.model.ServiceCategoryModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceCategoryMapper implements Mapper<ServiceCategoryModel, ServiceCategoryDTO>{
    @Override
    public ServiceCategoryModel toModel(ServiceCategoryDTO dto, boolean withId) {
        return new ServiceCategoryModel(
                withId ? dto.getServiceCategoryId() : null,
                dto.getCategoryName(),
                dto.getCategoryDescription()
        );
    }

    @Override
    public ServiceCategoryDTO toDTO(ServiceCategoryModel model) {
        return new ServiceCategoryDTO(
                model.getServiceCategoryId(),
                model.getCategoryName(),
                model.getCategoryDescription()
        );
    }
}
