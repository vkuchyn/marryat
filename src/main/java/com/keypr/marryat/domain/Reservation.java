package com.keypr.marryat.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@EqualsAndHashCode(of = {"firstName", "lastName", "room", "start", "end"})
@AllArgsConstructor
public class Reservation {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String room;
    private final LocalDate start;
    private final LocalDate end;

    public Reservation(
            final String firstName, final String lastName, final String room,
            final LocalDate start, final LocalDate end
    ) {
        this(null, firstName, lastName, room, start, end);
    }

}
