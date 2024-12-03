package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.EmployeeQualificationDTO;
import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.service.EmployeeService;
import com.zpi.fryzland.service.ServiceCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeQualificationMapper implements Mapper<EmployeeQualificationModel, EmployeeQualificationDTO> {

    private final EmployeeService employeeService;
    private final ServiceCategoryService serviceCategoryService;

    @Override
    public EmployeeQualificationModel toModel(EmployeeQualificationDTO dto, boolean withId){
        return new EmployeeQualificationModel(
                dto.getEmployeeQualificationID(),
                dto.getServiceCategoryID() != null ? serviceCategoryService.getCategoryById(dto.getServiceCategoryID()).orElse(null) : null,
                dto.getEmployeeID() != null ? employeeService.getEmployeeById(dto.getEmployeeID()).orElse(null) : null
        );
    }

    @Override
    public EmployeeQualificationDTO toDTO(EmployeeQualificationModel model){
        return new EmployeeQualificationDTO(
            model.getEmployeeQualificationID(),
            model.getServiceCategoryModel() != null ? model.getServiceCategoryModel().getServiceCategoryId() : null,
            model.getEmployeeModel() != null ? model.getEmployeeModel().getEmployeeID() : null
        );
    }
}
