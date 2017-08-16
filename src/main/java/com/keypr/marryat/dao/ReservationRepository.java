package com.keypr.marryat.dao;

import com.keypr.marryat.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

/**
 * Spring data repository, provides api for reservation to long storage.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Returns count of room reserved at specified period.
     *
     * @param room  Room number.
     * @param start Start date of reservation.
     * @param end   End date of reservation.
     * @return count of founded reservations.
     */
    @Query("SELECT count(p) FROM Reservation p WHERE room=?1 AND (p.start >= ?2 AND p.start <= ?3)")
    int countByRoomAndDateRange(String room, LocalDate start, LocalDate end);
}
