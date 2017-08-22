package com.keypr.marryat.service;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.dao.ReservationRepository;
import com.keypr.marryat.domain.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        validateReservation(reservation);
        return reservationRepository.save(reservation).getId();
    }

    private void validateReservation(Reservation reservation) {
        final int roomReservations = this.reservationRepository.countByRoomAndDateRange(
                reservation.getId(), reservation.getRoom(), reservation.getStart(), reservation.getEnd()
        );
        if (roomReservations > 0) {
            throw new ApplicationException();
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        validateReservation(reservation);
        final Reservation existing = reservationRepository.findOne(reservation.getId());
        if (existing == null) {
            throw new ApplicationException();
        }
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> allReservations(final LocalDate from, final LocalDate to, final int page, final int size) {
        return reservationRepository.findByStartBetween(from, to, new PageRequest(page, size)).getContent();
    }

    @Override
    public Reservation removeReservation(Long id) {
        throw new UnsupportedOperationException();
    }
}
