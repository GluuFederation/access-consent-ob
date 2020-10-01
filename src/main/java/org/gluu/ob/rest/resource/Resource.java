package org.gluu.ob.rest.resource;

import org.gluu.ob.rest.model.idp.IntrospectionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public class Resource {

    @Context
    HttpServletRequest context;

    public String getClientId() {
        if (context.getAttribute("introspection") != null) {
            IntrospectionResponse introspection = (IntrospectionResponse) context.getAttribute("introspection");
            return introspection.getClientId();
        }
        return null;
    }

}
