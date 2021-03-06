package org.gluu.ob.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consent {

    private String consentId;
    private String profile;
    private List<String> permissions;
    private List<String> accounts;
    private String clientId;
    private String userId;
    private String status;
    private String statusUpdateDatetime;
    private String creationDatetime;
    private String expirationDatetime;
    private String transactionFromDatetime;
    private String transactionToDatetime;

}
