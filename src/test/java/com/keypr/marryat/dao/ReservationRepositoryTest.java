package com.keypr.marryat.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.keypr.marryat.domain.Reservation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Testing db layer reservation methods.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    private static final LocalDate TODAY = LocalDate.of(2017, 8, 14);

    @Autowired
    private ReservationRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(() -> jdbcTemplate.getDataSource().getConnection());

    @Test
    @DataSet("/dbunit/empty.xml")
    @ExpectedDataSet("/dbunit/ReservationRepositoryTest/savesReservation_expected.xml")
    @Commit
    public void savesReservation() throws Exception {
        final Reservation reservation = new Reservation("first", "last", "room", TODAY, TODAY);
        repository.save(reservation);
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsZeroReservations() throws Exception {
        final int count = repository.countByRoomAndDateRange("7",
                LocalDate.of(2017, 8, 17), LocalDate.of(2017, 8, 18)
        );

        assertThat(count, is(0));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsZeroWhenReservationsStartAtSameDayPreviousEnds() throws Exception {
        final int count = repository.countByRoomAndDateRange("7",
                LocalDate.of(2017, 8, 13), LocalDate.of(2017, 8, 14)
        );

        assertThat(count, is(0));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsOneReservationWhenStartDateBetweenBookedDates() throws Exception {
        final int count = repository.countByRoomAndDateRange("7",
                LocalDate.of(2017, 8, 16), LocalDate.of(2017, 8, 18)
        );

        assertThat(count, is(1));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsOneReservationWhenEndDateBetweenBookedDates() throws Exception {
        final int count = repository.countByRoomAndDateRange("7",
                LocalDate.of(2017, 8, 13), LocalDate.of(2017, 8, 15)
        );

        assertThat(count, is(1));
    }
}