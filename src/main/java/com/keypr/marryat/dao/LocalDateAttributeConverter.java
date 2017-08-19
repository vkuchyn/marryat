package com.keypr.marryat.dao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * {@link LocalDate} <-> {@link Date} attribute converter for JPA.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}
