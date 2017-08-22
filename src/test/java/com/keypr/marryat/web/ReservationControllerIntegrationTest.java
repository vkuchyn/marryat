package com.keypr.marryat.web;

import com.keypr.marryat.commons.ApplicationException;
import com.keypr.marryat.commons.Clock;
import com.keypr.marryat.commons.NotFoundException;
import com.keypr.marryat.domain.Reservation;
import com.keypr.marryat.service.ReservationService;
import com.keypr.marryat.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.keypr.marryat.web.commons.FakeClock.FIXED_CURRENT_TIME;
import static java.util.Arrays.asList;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration reservation controller test with mocked services.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@ActiveProfiles("test")
public final class ReservationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Clock clock;

    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RoomService roomService;

    @Before
    public void setUpMocks() {
        when(this.clock.date()).thenReturn(FIXED_CURRENT_TIME.toLocalDate());
        when(this.roomService.allRooms()).thenReturn(asSet("23A", "23B"));
    }

    @Test //TODO(vkuchyn) extract massive jsons to resource files.
    public void validatesWrongDates() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23A\"," +
                                "\"start_date\": \"20170814\"," +
                                "\"end_date\":  \"20170814\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("[{" +
                        "\"errorKey\":\"end.date.after.start.date\"," +
                        "\"description\":\"End date should be after start date\"" +
                        "}]"));
    }

    @Test
    public void validatesWrongDatesOnUpdate() throws Exception {
        mockMvc.perform(
                put("/reservations/1")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23A\"," +
                                "\"start_date\": \"20170814\"," +
                                "\"end_date\":  \"20170814\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void validatesStartDateIsAfterOrEqualsToday() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23B\"," +
                                "\"start_date\": \"20170813\"," +
                                "\"end_date\":  \"20170816\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("[{" +
                        "\"errorKey\":\"start.date.after.today\"," +
                        "\"description\":\"Start date should be after or equals today\"" +
                        "}]"));
    }

    @Test
    public void validatesNullInputs() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content("{}")
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json('[' +
                        "{\"errorKey\":\"start.date.not.null\"," +
                        "\"description\":\"Expecting start date format apply pattern 'yyyyMMdd' and may not be " +
                        "null\"}," +
                        "{\"errorKey\":\"end.date.not.null\"," +
                        "\"description\":\"Expecting end date format apply pattern 'yyyyMMdd' and may not be null\"}," +
                        "{\"errorKey\":\"room.date.not.null\",\"description\":\"Expecting room not null value\"}," +
                        "{\"errorKey\":\"first.name.not.null\"," +
                        "\"description\":\"Expecting first name not null value\"}," +
                        "{\"errorKey\":\"last.name.not.null\"," +
                        "\"description\":\"Expecting last name not null value\"}" +
                        ']')
                );
    }

    @Test
    public void fetchesAllReservationsWithPagination() throws Exception {
        final LocalDate from = LocalDate.of(2017, 8, 14);
        final LocalDate to = LocalDate.of(2017, 8, 16);
        when(reservationService.allReservations(
                from, to, 0, 2
                )
        ).thenReturn(asList(new Reservation("first", "last", "25", from, to)));
        mockMvc.perform(
                get("/reservations")
                        .contentType("application/json")
                        .param("from", "20170814")
                        .param("to", "20170816")
                        .param("page", "0")
                        .param("size", "2")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "\"first_name\": \"first\"," +
                        "\"last_name\": \"last\"," +
                        "\"room\": \"25\"," +
                        "\"start_date\": \"20170814\"," +
                        "\"end_date\": \"20170816\"" +
                        "}]")
                );
    }

    @Test
    public void fetchesAllReservationsWithDefaultPageSize() throws Exception {
        mockMvc.perform(
                get("/reservations")
                        .contentType("application/json")
                        .param("from", "20170814")
                        .param("to", "20170816")
        )
                .andDo(print())
                .andExpect(status().isOk());

        verify(reservationService).allReservations(
                LocalDate.of(2017, 8, 14), LocalDate.of(2017, 8, 16),
                0, Integer.MAX_VALUE
        );
    }

    @Test
    public void expectBadRequestOnMissingDateParams() throws Exception {
        mockMvc.perform(
                get("/reservations")
                        .contentType("application/json")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void removesReservation() throws Exception {
        final LocalDate date = LocalDate.of(2017, 8, 14);
        when(reservationService.removeReservation(1L)).thenReturn(new Reservation(1L, "first", "last", "23", date,
                date));
        mockMvc.perform(
                delete("/reservations/1")
                        .contentType("application/json")
        )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        '{' +
                                "\"id\":1," +
                                "\"first_name\": \"first\"," +
                                "\"last_name\": \"last\"," +
                                "\"room\": \"23\"," +
                                "\"start_date\": \"20170814\"," +
                                "\"end_date\":  \"20170814\"" +
                                '}')
                );
    }

    @Test
    public void expectsNotFountOnWrongReservationId() throws Exception {
        final LocalDate date = LocalDate.of(2017, 8, 14);
        when(reservationService.removeReservation(1L)).thenThrow(
                new NotFoundException("reservation.not.found", "Could not found reservation with id 1")
        );
        mockMvc.perform(
                delete("/reservations/1")
                        .contentType("application/json")
        )
                .andExpect(status().isNotFound())
                .andExpect(content().json(
                        '{' +
                                "\"errorKey\":\"reservation.not.found\"," +
                                "\"description\": \"Could not found reservation with id 1\"" +
                                '}')
                );
    }

    @Test
    public void expectsBadRequestWhenRoomIsAlreadyReservedForReservation() throws Exception {
        when(reservationService.reserveRoom(any(Reservation.class))).thenThrow(
                new ApplicationException("room.reserved.for.dates", "Room is already reserved for posted dates")
        );
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23B\"," +
                                "\"start_date\": \"20170815\"," +
                                "\"end_date\":  \"20170816\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json('{' +
                        "\"errorKey\":\"room.reserved.for.dates\"," +
                        "\"description\":\"Room is already reserved for posted dates\"" +
                        '}')
                );
    }

    @Test
    public void expectsBadRequestOnWrongRoom() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"invalid_room\"," +
                                "\"start_date\": \"20170815\"," +
                                "\"end_date\":  \"20170816\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("[{" +
                        "\"errorKey\":\"room.not.exists\"," +
                        "\"description\":\"Room invalid_room not exists in hotel\"" +
                        "}]")
                );
    }
}