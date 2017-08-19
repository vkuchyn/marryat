package com.keypr.marryat.service;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.dao.ReservationRepository;
import com.keypr.marryat.domain.Reservation;
import org.junit.Before;
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
    public static final LocalDate TOMORROW = TODAY.plusDays(1L);
    @Mock
    private ReservationRepository repository;
    private ReservationService service;

    @Before
    public void setUp() throws Exception {
        service = new DefaultReservationService(repository);
    }

    @Test
    public void returnsGeneratedId() throws Exception {
        final Reservation entity = mock(Reservation.class);
        when(repository.save(entity)).thenReturn(new Reservation(7L, "", "", "", TODAY, TODAY));

        final Long id = service.reserveRoom(entity);
        assertThat(id, is(7L));
        verify(repository).save(entity);
    }

    @Test(expected = ApplicationException.class)
    public void throwsExceptionWhenRoomIsAlreadyReserved() throws Exception {
        final Reservation entity = new Reservation("", "", "room", TODAY, TOMORROW);
        when(repository.countByRoomAndDateRange("room", TODAY, TOMORROW)).thenReturn(1);

        service.reserveRoom(entity);
    }
}