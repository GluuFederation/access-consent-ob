package org.gluu.ob.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.gluu.ob.util.ApiConstants;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consent {

    private String consentId;
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
