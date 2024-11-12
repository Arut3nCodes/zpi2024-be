package com.zpi.fryzland.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceDTO {
    private Integer serviceID;
    private String serviceName;
    private Integer serviceSpan;
    private Float servicePrice;
    private String serviceDescription;
    private Integer serviceCategoryID;
}
