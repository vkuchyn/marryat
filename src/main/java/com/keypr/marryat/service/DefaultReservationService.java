package com.keypr.marryat.service;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.dao.ReservationRepository;
import com.keypr.marryat.domain.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Main implementation of service, provides transaction support and business logic rules.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@Service
@AllArgsConstructor
public final class DefaultReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Long reserveRoom(final Reservation reservation) {
        final int roomReservations = this.reservationRepository.countByRoomAndDateRange(
                reservation.getRoom(), reservation.getStart(), reservation.getEnd()
        );
        if (roomReservations > 0) {
            throw new ApplicationException();
        }
        return reservationRepository.save(reservation).getId();
    }
}
