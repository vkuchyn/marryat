package com.keypr.marryat.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.keypr.marryat.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

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

    @Test
    @DataSet("/dbunit/empty.xml")
    @ExpectedDataSet("/dbunit/ReservationRepositoryTest/savesReservation_expected.xml")
    public void savesReservation() throws Exception {
        final Reservation reservation = new Reservation("first", "last", "room", TODAY, TODAY);
        repository.save(reservation);
    }
}