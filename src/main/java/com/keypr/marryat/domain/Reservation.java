package com.keypr.marryat.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Domain class describes reservation entity.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"firstName", "lastName", "room", "start", "end"})
@ToString
@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String room;
    private LocalDate start;
    private LocalDate end;

    public Reservation(
            final String firstName, final String lastName, final String room,
            final LocalDate start, final LocalDate end
    ) {
        this(null, firstName, lastName, room, start, end);
    }

}
