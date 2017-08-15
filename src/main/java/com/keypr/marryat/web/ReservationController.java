package com.keypr.marryat.web;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Rest controller responsible for reservations CRUD manipulations and retrieving.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
@Validated
public class ReservationController {

    @PostMapping("/reservations")
    public void reserveRoom(@Valid @RequestBody final ReservationView reservation, Errors errors) {
        //TODO(vkuchyn) implement feature
    }
}
