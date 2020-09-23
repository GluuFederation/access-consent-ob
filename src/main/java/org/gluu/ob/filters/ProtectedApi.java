/**
 *
 */
package org.gluu.ob.filters;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ProtectedApi {

    /**
     * @return UMA scopes which application should have to access access-consent api.
     */
    String[] scopes() default {};

}
