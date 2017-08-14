package com.keypr.marryat.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Handles user data invalid exceptions aka {@link ConstraintViolationException}
 * and {@link javax.validation.ValidationException}.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorView> handleConstraintViolationException(final ConstraintViolationException violations) {
        return violations.getConstraintViolations().stream()
                .map(violation -> new ErrorView(
                        formatMessageTemplate(violation.getMessageTemplate()),
                        violation.getMessage())
                )
                .collect(toList());
    }

    // Just remove first and last symbol assuming we have template in format `{<error_key}`
    private String formatMessageTemplate(final String messageTemplate) {
        return messageTemplate.substring(1, messageTemplate.length() - 1);
    }
}
