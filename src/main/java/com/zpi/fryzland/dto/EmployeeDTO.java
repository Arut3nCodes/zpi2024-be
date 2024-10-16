package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private Integer employeeID;
    private String employeeName;
    private String employeeSurname;
    private String employeeDialNumber;
    private String encryptedEmployeePassword;
    private String employeeEmail;
    private LocalDate employeeBirthdayDate;
    private LocalDate employeeEmploymentDate;
    private Float employeeMonthlyPay;
    private String employeeCity;
    private String employeeStreet;
    private String employeeBuildingNumber;
    private String employeeApartmentNumber;
    private String employeePostalCode;
}
