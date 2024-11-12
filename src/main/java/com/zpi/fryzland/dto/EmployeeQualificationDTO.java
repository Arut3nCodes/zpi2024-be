package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeQualificationDTO {
    private Integer employeeQualificationID;
    private Integer serviceCategoryID;
    private Integer employeeID;
}
