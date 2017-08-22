package com.keypr.marryat.web;

import com.keypr.marryat.domain.Reservation;
import com.keypr.marryat.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Rest controller responsible for reservations CRUD manipulations and retrieving.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(value = "/reservations", consumes = "application/json", produces = "application/json")
@Validated
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Map<String, Long> reserveRoom(@Valid @RequestBody final ReservationView reservationView, final Errors
            errors) {
        final Reservation reservation = mapViewToEntity(null, reservationView);
        final Long reservationId = reservationService.reserveRoom(reservation);

        Map<String, Long> result = new HashMap<>();
        result.put("id", reservationId);
        return result;
    }

    @GetMapping
    public List<ReservationView> fetchesReservationsByFilter(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate to,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "0x7fffffff") Integer size
    ) {
        return reservationService.allReservations(from, to, page, size)
                .stream()
                .map(entity -> mapEntityToView(entity)).collect(toList());
    }

    @PutMapping("/{id}")
    public void updateReservation(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final ReservationView reservationView,
            final Errors errors
    ) {
        final Reservation reservation = mapViewToEntity(id, reservationView);
        reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public ReservationView removeReservation(
            @PathVariable("id") final Long id
    ) {
        final Reservation reservation = reservationService.removeReservation(id);
        return mapEntityToView(reservation);
    }

    private Reservation mapViewToEntity(@PathVariable("id") Long id, @Valid @RequestBody ReservationView
            reservationView) {
        return new Reservation(
                id, reservationView.getFirstName(), reservationView.getLastName(),
                reservationView.getRoomNumber(), reservationView.getStart(), reservationView.getEnd()
        );
    }

    private ReservationView mapEntityToView(Reservation entity) {
        return new ReservationView(
                entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getRoom(),
                entity.getStart(), entity.getEnd()
        );
    }
}
