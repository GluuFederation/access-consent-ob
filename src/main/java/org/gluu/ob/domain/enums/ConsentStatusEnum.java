package org.gluu.ob.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsentStatusEnum {

    AWAITING_AUTHORISATION("AwaitingAuthorisation", ""),
    REJECTED("Rejected", "Reject"),
    AUTHORISED("Authorised", "Authorise"),
    REVOKED("Revoked", ""),
    EXPIRED("Expired", "");

    private final String value;
    private final String action;

}
