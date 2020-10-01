package org.gluu.ob.rest.model.idp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IntrospectionResponse {

    private String sub;
    private String aud;
    private List<String> scope;
    private String iss;
    private boolean active;
    private Long exp;
    private Long iat;
    private String jti;
    private String username;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "client_id")
    private String clientId;

    @JsonProperty(value = "acr_values")
    private String acrValues;

    @JsonProperty(value = "error_type")
    private String errorType;

    @JsonProperty(value = "error_description")
    private String errorDescription;

    @JsonProperty(value = "error_uri")
    private String errorUri;

}
