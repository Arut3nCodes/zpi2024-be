package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements Mapper<EmployeeModel, EmployeeDTO>{
    @Override
    public EmployeeDTO toDTO(EmployeeModel model){
        return new EmployeeDTO(
                model.getEmployeeID(),
                model.getEmployeeName(),
                model.getEmployeeSurname(),
                model.getEmployeeDialNumber(),
                model.getEncryptedEmployeePassword(),
                model.getEmployeeEmail(),
                model.getEmployeeBirthdayDate(),
                model.getEmployeeEmploymentDate(),
                model.getEmployeeMonthlyPay(),
                model.getEmployeeCity(),
                model.getEmployeeStreet(),
                model.getEmployeeBuildingNumber(),
                model.getEmployeeApartmentNumber(),
                model.getEmployeePostalCode()
        );
    }
    @Override
    public EmployeeModel toModel(EmployeeDTO dto, boolean withId){
        return new EmployeeModel(
                withId ? dto.getEmployeeID() : null,
                dto.getEmployeeName(),
                dto.getEmployeeSurname(),
                dto.getEmployeeDialNumber(),
                dto.getEncryptedEmployeePassword(),
                dto.getEmployeeEmail(),
                dto.getEmployeeBirthdayDate(),
                dto.getEmployeeEmploymentDate(),
                dto.getEmployeeMonthlyPay(),
                dto.getEmployeeCity(),
                dto.getEmployeeStreet(),
                dto.getEmployeeBuildingNumber(),
                dto.getEmployeeApartmentNumber(),
                dto.getEmployeePostalCode()
        );
    }
}
