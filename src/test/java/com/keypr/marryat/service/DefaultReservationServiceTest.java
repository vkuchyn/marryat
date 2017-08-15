package com.keypr.marryat.service;

import com.keypr.marryat.dao.ReservationRepository;
import com.keypr.marryat.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit test of service behaviour with mocked dependencies.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultReservationServiceTest {

    public static final LocalDate TODAY = LocalDate.of(2017, 8, 14);
    @Mock
    private ReservationRepository repository;

    @Test
    public void reservesRoomSuccessfully() throws Exception {
        final ReservationService service = new DefaultReservationService(repository);
        final Reservation entity = mock(Reservation.class);

        service.reserveRoom(entity);
        verify(repository).save(entity);
    }

    @Test
    public void returnsGeneratedId() throws Exception {
        final ReservationService service = new DefaultReservationService(repository);
        final Reservation entity = mock(Reservation.class);
        when(repository.save(entity)).thenReturn(new Reservation(7L, "", "", "", TODAY, TODAY));

        final Long id = service.reserveRoom(entity);
        assertThat(id, is(7L));
    }
}