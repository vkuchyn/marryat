package com.keypr.marryat.service;

import com.keypr.marryat.dao.RoomRepository;
import com.keypr.marryat.domain.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SimpleRoomService implements RoomService {

    private RoomRepository repository;

    @Override
    public Set<String> allRooms() {
        return repository.findAll().stream().map(Room::getName).collect(toSet());
    }
}
