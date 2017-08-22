package com.keypr.marryat.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.keypr.marryat.MarryatConfiguration;
import com.keypr.marryat.domain.Reservation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Testing db layer reservation methods.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = MarryatConfiguration.class)
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    public static final LocalDate SEVENTEENTH_AUGUST = LocalDate.of(2017, 8, 17);
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
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 17), LocalDate.of(2017, 8, 18)
        );

        final List<Reservation> all = repository.findAll();
        assertThat(count, is(0));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsZeroWhenReservationsStartAtSameDayPreviousEnds() throws Exception {
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 13), LocalDate.of(2017, 8, 14)
        );

        assertThat(count, is(0));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/countsOneReservationWhenStartDateBetweenBookedDates_initial.xml")
    public void countsOneReservationWhenStartDateBetweenBookedDates() throws Exception {
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 16), LocalDate.of(2017, 8, 18)
        );

        assertThat(count, is(1));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsOneReservationWhenEndDateBetweenBookedDates() throws Exception {
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 13), LocalDate.of(2017, 8, 15)
        );

        assertThat(count, is(1));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    public void countsZeroWhenIdEquals() throws Exception {
        final int count = repository.countByRoomAndDateRange(-1L, "7",
                LocalDate.of(2017, 8, 13), LocalDate.of(2017, 8, 15)
        );

        assertThat(count, is(0));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/countsOneWhenNewReservationInsideExisting_initial.xml")
    public void countsOneWhenNewReservationInsideExisting() throws Exception {
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 11), LocalDate.of(2017, 8, 12)
        );

        assertThat(count, is(1));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/countsOneWhenEndDateInRange_initial.xml")
    public void countsOneWhenEndDateInRange() throws Exception {
        final int count = repository.countByRoomAndDateRange(null, "7",
                LocalDate.of(2017, 8, 16), LocalDate.of(2017, 8, 18)
        );

        assertThat(count, is(1));
    }


    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/findsAllReservationsByDateRangeAndPage_initial.xml")
    public void findsAllReservationsByDateRange() throws Exception {
        final Page<Reservation> actual = repository.findByStartBetween(
                TODAY, SEVENTEENTH_AUGUST, new PageRequest(0, 6)
        );

        final List<Long> actualIdentifiers = actual.getContent().stream().map(Reservation::getId).collect(toList());
        assertThat(actualIdentifiers, is(asList(-15L, -14L, -13L, -12L, -11L, -10L)));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/findsAllReservationsByDateRangeAndPage_initial.xml")
    public void findsFirstPageReservationsByDateRange() throws Exception {
        final Page<Reservation> actual = repository.findByStartBetween(
                TODAY, SEVENTEENTH_AUGUST, new PageRequest(1, 1)
        );

        final List<Long> actualIdentifiers = actual.getContent().stream().map(Reservation::getId).collect(toList());
        assertThat(actualIdentifiers, is(asList(-14L)));
    }

    @Test
    @DataSet("/dbunit/ReservationRepositoryTest/reservations_initial.xml")
    @ExpectedDataSet("/dbunit/ReservationRepositoryTest/deletesReservation_expected.xml")
    @Commit
    public void deletesReservation() throws Exception {
        repository.delete(-1L);
    }
}