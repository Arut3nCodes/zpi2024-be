package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.service.ServiceCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper implements Mapper<CustomerModel, CustomerDTO>{

    private final ServiceCategoryService serviceCategoryService;

    @Override
    public CustomerModel toModel(CustomerDTO dto, boolean withId){
        return new CustomerModel(
                withId ? dto.getCustomerID() : null,
                dto.getCustomerName(),
                dto.getCustomerSurname(),
                dto.getCustomerDialNumber(),
                dto.getEncryptedCustomerPassword(),
                dto.getCustomerEmail(),
                dto.getServiceCategoryID() != null ? serviceCategoryService.getCategoryById(dto.getServiceCategoryID()).orElse(null) : null
        );
    }

    @Override
    public CustomerDTO toDTO(CustomerModel model){
        return new CustomerDTO(
                model.getCustomerID(),
                model.getCustomerName(),
                model.getCustomerSurname(),
                model.getCustomerDialNumber(),
                model.getEncryptedCustomerPassword(),
                model.getCustomerEmail(),
                model.getServiceCategoryModel() != null ? model.getServiceCategoryModel().getServiceCategoryId() : null
        );
    }
}
