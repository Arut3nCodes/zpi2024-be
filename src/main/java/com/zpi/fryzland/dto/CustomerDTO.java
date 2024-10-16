package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    private Integer customerID;
    private String customerName;
    private String customerSurname;
    private String customerDialNumber;
    private String encryptedCustomerPassword;
    private String customerEmail;
    private Integer serviceCategoryID;
}
