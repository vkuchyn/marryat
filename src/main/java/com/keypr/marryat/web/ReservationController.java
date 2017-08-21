package com.keypr.marryat.web;

import com.keypr.marryat.domain.Reservation;
import com.keypr.marryat.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Long> reserveRoom(@Valid @RequestBody final ReservationView reservationView, final Errors
            errors) {
        final Reservation reservation = new Reservation(
                reservationView.getFirstName(), reservationView.getLastName(),
                reservationView.getRoomNumber(), reservationView.getStart(), reservationView.getEnd()
        );
        final Long reservationId = reservationService.reserveRoom(reservation);

        Map<String, Long> result = new HashMap<>();
        result.put("id", reservationId);
        return result;
    }

    @PutMapping("/reservations/{id}")
    public void updateReservation(
            @PathVariable("id") final Long id,
            @RequestBody final ReservationView reservationView
    ) {
        final Reservation reservation = new Reservation(
                id, reservationView.getFirstName(), reservationView.getLastName(),
                reservationView.getRoomNumber(), reservationView.getStart(), reservationView.getEnd()
        );
        reservationService.updateReservation(reservation);
    }
}
