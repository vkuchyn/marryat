package com.keypr.marryat.service;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.commons.NotFoundException;
import com.keypr.marryat.dao.ReservationRepository;
import com.keypr.marryat.domain.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void updateReservation(Reservation reservation) {
        validateReservation(reservation);
        final Long id = reservation.getId();
        Optional.ofNullable(reservationRepository.findOne(id)).orElseThrow(
                () -> new NotFoundException("reservation.not.found", "Could not found reservation with id " + id)
        );

        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> allReservations(final LocalDate from, final LocalDate to, final int page, final int size) {
        return reservationRepository.findByStartBetween(from, to, new PageRequest(page, size)).getContent();
    }

    @Override
    public Reservation removeReservation(final Long id) {
        final Reservation removed = Optional.ofNullable(reservationRepository.findOne(id)).orElseThrow(
                () -> new NotFoundException("reservation.not.found", "Could not found reservation with id " + id)
        );
        reservationRepository.delete(id);
        return removed;
    }

    private void validateReservation(Reservation reservation) {
        final int roomReservations = this.reservationRepository.countByRoomAndDateRange(
                reservation.getId(), reservation.getRoom(), reservation.getStart(), reservation.getEnd()
        );
        if (roomReservations > 0) {
            throw new ApplicationException("room.reserved.for.dates", "Room is already reserved for posted dates");
        }
    }
}

