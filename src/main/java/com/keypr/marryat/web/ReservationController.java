package com.keypr.marryat.web;

import com.keypr.marryat.domain.Reservation;
import com.keypr.marryat.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest controller responsible for reservations CRUD manipulations and retrieving.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
@Validated
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ReservationView reserveRoom(@Valid @RequestBody final ReservationView reservationView, final Errors errors) {
        final Reservation reservation = new Reservation(
                reservationView.getFirstName(), reservationView.getLastName(),
                reservationView.getRoomNumber(), reservationView.getStart(), reservationView.getEnd()
        );
        final Long reservationId = reservationService.reserveRoom(reservation);
        return new ReservationView(reservationId, reservationView);
    }

    @PutMapping("/reservations/{id}")
    public ReservationView updateReservation(
            @PathVariable("id") Long id,
            @RequestBody ReservationView reservationView
    ) {
        return reservationView;
    }
}
