package com.keypr.marryat.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ValidationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Jackson lodal date deserializer.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public final class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateDeserializer.class);

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public LocalDate deserialize(final JsonParser parser, final DeserializationContext context) {
        try {
            return LocalDate.parse(parser.getText(), formatter);
        } catch (IOException e) {
            final String message = "Could not parse JSON";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(message);
            }
            throw new ValidationException(message, e);
        }
    }
}

