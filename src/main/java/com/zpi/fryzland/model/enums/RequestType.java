package com.zpi.fryzland.model.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum RequestType {
    PASSWORD_CHANGE("PASSWORD_CHANGE"),
    ACCOUNT_ACTIVATION("ACCOUNT_ACTIVATION");
    private final String REQUEST_TYPE;
}
