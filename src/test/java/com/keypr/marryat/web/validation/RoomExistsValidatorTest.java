package com.keypr.marryat.web.validation;

import com.keypr.marryat.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.mockito.Mockito.when;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomExistsValidatorTest {

    @Mock
    private RoomService roomService;

    @Test
    public void validatesExistingRoom() throws Exception {
        when(roomService.allRooms()).thenReturn(asSet("12", "24"));
        final RoomExistsValidator validator = new RoomExistsValidator(roomService);

        assertThat(validator.isValid("12", null), is(true));
    }

    @Test
    public void invalidatesNotExistingRoom() throws Exception {
        when(roomService.allRooms()).thenReturn(asSet("12", "24"));
        final RoomExistsValidator validator = new RoomExistsValidator(roomService);

        assertThat(validator.isValid("34", null), is(false));
    }

    @Test
    public void validatesNullRoom() throws Exception {
        when(roomService.allRooms()).thenReturn(asSet("12", "24"));
        final RoomExistsValidator validator = new RoomExistsValidator(roomService);

        assertThat(validator.isValid(null, null), is(true));
    }
}
