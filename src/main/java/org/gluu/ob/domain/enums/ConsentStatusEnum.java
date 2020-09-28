package org.gluu.ob.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsentStatusEnum {

    AWAITING_AUTHORISATION("AwaitingAuthorisation"),
    REJECTED("Rejected"),
    AUTHORISED("Authorised"),
    REVOKED("Revoked");

    private final String value;

}
