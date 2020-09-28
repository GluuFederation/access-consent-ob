package org.gluu.ob.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalError extends Exception {

    public String description;

    public InternalError(String description) {
        super();
        this.description = description;
    }

}
