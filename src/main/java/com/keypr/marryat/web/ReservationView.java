package com.keypr.marryat.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.keypr.marryat.web.validation.DateRange;
import com.keypr.marryat.web.validation.FutureOrToday;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
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

    private static final long DEFAULT_ID = 0L;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("room")
    private String roomNumber;
    @JsonProperty("start_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "{start.date.not.null}")
    @FutureOrToday
    private LocalDate start;
    @JsonProperty("end_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "{end.date.not.null}")
    private LocalDate end;

    public ReservationView(
            final String firstName, final String lastName, final String roomNumber,
            final LocalDate start, final LocalDate end
    ) {
        this(DEFAULT_ID, firstName, lastName, roomNumber, start, end);
    }

    public ReservationView(final Long id, final ReservationView view) {
        this(id, view.getFirstName(), view.getLastName(), view.getRoomNumber(), view.getStart(), view.getEnd());
    }
}
