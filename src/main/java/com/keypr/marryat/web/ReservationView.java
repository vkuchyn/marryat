package com.keypr.marryat.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.keypr.marryat.web.validation.DateRange;
import com.keypr.marryat.web.validation.FutureOrToday;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents json model for reservation.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Data
@AllArgsConstructor
@DateRange(message = "{end.date.after.start.date}")
public final class ReservationView {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("room")
    private String roomNumber;

    @JsonProperty("start_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @FutureOrToday
    private LocalDate start;

    @JsonProperty("end_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate end;

}
