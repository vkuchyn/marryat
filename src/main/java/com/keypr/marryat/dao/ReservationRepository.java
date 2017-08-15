package com.keypr.marryat.dao;

import com.keypr.marryat.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data repository, provides api for reservation to long storage.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
