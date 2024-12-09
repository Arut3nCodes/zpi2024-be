package com.zpi.fryzland.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VisitStatus {
    RESERVED("RESERVED"),
    CANCELLED_EMPLOYEE("CANCELLED_EMPLOYEE"),
    CANCELLED_CUSTOMER("CANCELLED_CUSTOMER"),
    DONE("DONE");
    private String value;
}
