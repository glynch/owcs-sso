package io.github.glynch.owcs.sso;

import org.springframework.core.NestedRuntimeException;

/**
 * 
 */
public class SSOException extends NestedRuntimeException {

    public SSOException(String message) {
        super(message);
    }

    public SSOException(String message, Throwable cause) {
        super(message, cause);
    }

}
