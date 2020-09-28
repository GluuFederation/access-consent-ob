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
public class PostConsent {

    private String profile;
    private List<String> permissions;
    private String expirationDatetime;
    private String transactionFromDatetime;
    private String transactionToDatetime;

}
