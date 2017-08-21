package com.keypr.marryat.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Serializes {@link LocalDate } values to {@link String} format for json representation.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    //TODO(vkuchyn) extract to common place.
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException {
        gen.writeString(value.format(formatter));
    }

    @Override
    public Class<LocalDate> handledType() {
        return LocalDate.class;
    }
}
