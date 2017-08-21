package com.keypr.marryat.service;

import com.keypr.marryat.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * Updates customer reservation if exists.
     *
     * @param reservation Entity.
     */
    void updateReservation(Reservation reservation);

    /**
     * Fetches all reservations by date range, page and size.
     *
     * @param from Reservations date starts.
     * @param to   Reservations date ends.
     * @param page Response page.
     * @param size Items per page.
     * @return all reservations for specified period according pagination.
     */
    List<Reservation> allReservations(LocalDate from, LocalDate to, int page, int size);
}
