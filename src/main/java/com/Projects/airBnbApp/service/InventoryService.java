package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.entity.Room;

public interface InventoryService {

    void insitlizeRoomForAYear(Room room);
    void deleteFutureInventory(Room room);

}
