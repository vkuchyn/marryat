package com.keypr.marryat.web;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.domain.Reservation;
import com.keypr.marryat.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test of controller using mockito for mocks.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationControllerUnitTest {

    private static final String FIRST_NAME = "first";
    private static final String SECOND_NAME = "second";
    private static final String ROOM = "room";
    private static final LocalDate TODAY = LocalDate.of(2017, 8, 14);
    private static final long GENERATED_ID = 1L;

    @Mock
    private ReservationService service;
    private ReservationController controller;

    @Before
    public void init() {
        this.controller = new ReservationController(service);
    }

    @Test
    public void reservesRoom() throws Exception {
        when(service.reserveRoom(new Reservation(FIRST_NAME, SECOND_NAME, ROOM, TODAY, TODAY)))
                .thenReturn(GENERATED_ID);
        final Map<String, Long> actual = this.controller.reserveRoom(
                new ReservationView(FIRST_NAME, SECOND_NAME, ROOM, TODAY, TODAY), null
        );
        final Map<String, Long> expected = new HashMap<>();
        expected.put("id", GENERATED_ID);
        assertThat(actual, is(expected));
    }

    @Test(expected = ApplicationException.class)
    public void rethrowsServiceApplicationException() throws Exception {
        when(service.reserveRoom(any(Reservation.class))).thenThrow(new ApplicationException());
        this.controller.reserveRoom(new ReservationView(FIRST_NAME, SECOND_NAME, ROOM, TODAY, TODAY), null);
    }
}