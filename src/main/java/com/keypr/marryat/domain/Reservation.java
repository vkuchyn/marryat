package com.keypr.marryat.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
