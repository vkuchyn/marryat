package com.keypr.marryat.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Json representation of exception. Contains error key with human readable description.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
@Data
public class ErrorView {

    private final String errorKey;
    private final String description;
}
