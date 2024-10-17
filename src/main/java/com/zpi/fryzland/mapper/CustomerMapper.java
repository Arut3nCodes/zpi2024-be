package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.model.CustomerModel;

//todo: Zrobić coś z tym nullem na pozycji ServiceCategoryModel w toModel()
public class CustomerMapper {
    public static CustomerDTO toDTO(CustomerModel model){
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

    public static CustomerModel toModel(CustomerDTO dto){
        return new CustomerModel(
                null,
                dto.getCustomerName(),
                dto.getCustomerSurname(),
                dto.getCustomerDialNumber(),
                dto.getEncryptedCustomerPassword(),
                dto.getCustomerEmail(),
                null
        );
    }
}
