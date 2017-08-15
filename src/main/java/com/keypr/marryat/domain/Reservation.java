package com.keypr.marryat.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Domain class describes reservation entity.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
@EqualsAndHashCode(of = {"firstName", "lastName", "room", "start", "end"})
@ToString
@Getter
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
