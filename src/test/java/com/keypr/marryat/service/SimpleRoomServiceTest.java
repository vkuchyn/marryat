package com.keypr.marryat.service;

import com.keypr.marryat.dao.RoomRepository;
import com.keypr.marryat.domain.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.mockito.Mockito.when;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleRoomServiceTest {

    @Mock
    private RoomRepository repository;

    @Test
    public void findsAllRoomNames() throws Exception {
        final RoomService service = new SimpleRoomService(repository);
        when(repository.findAll()).thenReturn(asList(new Room(1L, "1"), new Room(2L, "2")));

        final Set<String> actual = service.allRooms();
        assertThat(actual, is(asSet("1", "2")));
    }
}