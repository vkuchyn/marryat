package com.keypr.marryat.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.keypr.marryat.MarryatConfiguration;
import com.keypr.marryat.domain.Room;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = MarryatConfiguration.class)
@ActiveProfiles("test")
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(() -> jdbcTemplate.getDataSource().getConnection());

    @Test
    @DataSet("/dbunit/RoomRepositoryTest/findsAllRooms_initial.xml")
    public void findsAllRooms() throws Exception {
        final List<Room> rooms = this.repository.findAll();
        assertThat(rooms, is(asList(new Room(1L, "100"), new Room(2L, "101"))));
    }
}