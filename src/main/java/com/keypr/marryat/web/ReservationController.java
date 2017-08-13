package com.keypr.marryat.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller responsible for reservations CRUD manipulations and retrieving.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(consumes = "application/json")
public class ReservationController {

    @PostMapping("/reservations")
    public void reserveRoom(@RequestBody final String json) {
        //TODO(vkuchyn) implement feature
    }
}
