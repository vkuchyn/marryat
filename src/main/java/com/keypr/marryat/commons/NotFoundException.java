package com.keypr.marryat.commons;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public class NotFoundException extends ApplicationException {

    public NotFoundException(final String errorKey, final String message) {
        super(errorKey, message);
    }
}
