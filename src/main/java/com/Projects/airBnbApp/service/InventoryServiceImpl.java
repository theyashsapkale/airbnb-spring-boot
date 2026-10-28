package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.entity.Inventory;
import com.Projects.airBnbApp.entity.Room;
import com.Projects.airBnbApp.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    public void insitlizeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(1);
        for(; !today.isAfter(endDate); today.plusDays(1)){
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);


        }
    }

    @Override
    public void deleteFutureInventory(Room room) {
       LocalDate today = LocalDate.now();
       inventoryRepository.deleteByDateAfter(today, room);
    }

}
