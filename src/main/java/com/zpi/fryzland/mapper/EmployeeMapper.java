package com.zpi.fryzland.mapper;

import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(EmployeeModel employeeModel){
        return new EmployeeDTO(
                employeeModel.getEmployeeID(),
                employeeModel.getEmployeeName(),
                employeeModel.getEmployeeSurname(),
                employeeModel.getEmployeeDialNumber(),
                employeeModel.getEncryptedEmployeePassword(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getEmployeeBirthdayDate(),
                employeeModel.getEmployeeEmploymentDate(),
                employeeModel.getEmployeeMonthlyPay(),
                employeeModel.getEmployeeCity(),
                employeeModel.getEmployeeStreet(),
                employeeModel.getEmployeeBuildingNumber(),
                employeeModel.getEmployeeApartmentNumber(),
                employeeModel.getEmployeePostalCode()
        );
    }

    public static EmployeeModel toModel(EmployeeDTO employeeDTO){
        return new EmployeeModel(
                null,
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmployeeSurname(),
                employeeDTO.getEmployeeDialNumber(),
                employeeDTO.getEncryptedEmployeePassword(),
                employeeDTO.getEmployeeEmail(),
                employeeDTO.getEmployeeBirthdayDate(),
                employeeDTO.getEmployeeEmploymentDate(),
                employeeDTO.getEmployeeMonthlyPay(),
                employeeDTO.getEmployeeCity(),
                employeeDTO.getEmployeeStreet(),
                employeeDTO.getEmployeeBuildingNumber(),
                employeeDTO.getEmployeeApartmentNumber(),
                employeeDTO.getEmployeePostalCode()
        );
    }
}
