package com.Projects.airBnbApp.repository;

import com.Projects.airBnbApp.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Inventory,Long> {

}
