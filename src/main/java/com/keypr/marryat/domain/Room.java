package com.keypr.marryat.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Room entity, encapsulates all related to room domain information.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    private Long id;
    @Getter
    private String name;
}
