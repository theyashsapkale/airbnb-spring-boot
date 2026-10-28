package com.Projects.airBnbApp.repository;

import com.Projects.airBnbApp.entity.Inventory;
import com.Projects.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository  extends JpaRepository<Inventory,Long> {

       void deleteByDateAfter(LocalDate date, Room room);
}
