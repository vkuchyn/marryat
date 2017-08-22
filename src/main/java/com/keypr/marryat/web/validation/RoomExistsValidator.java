package com.keypr.marryat.web.validation;

import com.keypr.marryat.service.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * Validates if room exists in a hotel.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public final class RoomExistsValidator implements ConstraintValidator<RoomExists, String> {

    private final Set<String> rooms;

    public RoomExistsValidator(RoomService roomService) {
        this.rooms = roomService.allRooms();
    }

    @Override
    public void initialize(final RoomExists annotation) {

    }

    @Override
    public boolean isValid(final String room, final ConstraintValidatorContext context) {
        return room == null || rooms.contains(room);
    }
}
