package com.keypr.marryat.commons;

import lombok.NoArgsConstructor;

/**
 * Main application exception signals about unexpected scenario flow.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private String errorKey;

    public ApplicationException(final String errorKey, final String message) {
        super(message);
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }

}
