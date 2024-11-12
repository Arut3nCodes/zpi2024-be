package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.model.CustomerModel;
import org.springframework.stereotype.Component;

//todo: Zrobić coś z tym nullem na pozycji ServiceCategoryModel w toModel() w customerModel
@Component
public class CustomerMapper implements Mapper<CustomerModel, CustomerDTO>{

    //todo: Po ukończeniu serviceCategoryService uzupełnić customerModel
    @Override
    public CustomerModel toModel(CustomerDTO dto, boolean withId){
        return new CustomerModel(
                withId ? dto.getCustomerID() : null,
                dto.getCustomerName(),
                dto.getCustomerSurname(),
                dto.getCustomerDialNumber(),
                dto.getEncryptedCustomerPassword(),
                dto.getCustomerEmail(),
                null
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
