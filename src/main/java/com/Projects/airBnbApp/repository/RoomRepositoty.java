package com.Projects.airBnbApp.repository;

import com.Projects.airBnbApp.entity.Inventory;
import com.Projects.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepositoty extends JpaRepository<Room,Long> {

}
