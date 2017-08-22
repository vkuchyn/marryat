package com.keypr.marryat.dao;

import com.keypr.marryat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}
