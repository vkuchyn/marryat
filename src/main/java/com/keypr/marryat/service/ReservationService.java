package com.keypr.marryat.service;

import com.keypr.marryat.domain.Reservation;

/**
 * Provides api for CRUD operations with reservation and business logic validation.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public interface ReservationService {

    /**
     * Reserves room for customer, checks if room available for period.
     *
     * @param reservation Entity.
     * @return Id of reservation.
     */
    Long reserveRoom(Reservation reservation);
}
