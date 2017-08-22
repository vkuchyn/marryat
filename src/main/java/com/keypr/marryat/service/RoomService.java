package com.keypr.marryat.service;

import java.util.Set;

/**
 * Simple room service, provides set of all rooms.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
public interface RoomService {

    /**
     * Fetches all rooms in hotel.
     *
     * @return set of all rooms in hotel.
     */
    Set<String> allRooms();
}
