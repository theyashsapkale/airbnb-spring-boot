package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.DTO.RoomDto;
import com.Projects.airBnbApp.entity.Hotel;
import com.Projects.airBnbApp.entity.Room;
import com.Projects.airBnbApp.exception.ResourceNotFoundException;
import com.Projects.airBnbApp.repository.HotelRepository;
import com.Projects.airBnbApp.repository.InventoryRepository;
import com.Projects.airBnbApp.repository.RoomRepositoty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j


public class RoomServiceImpI implements  RoomServices{

    private final RoomRepositoty roomRepositoty;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId , RoomDto roomDto) {
        log.info("creating a new room in the hotel with ID:  {} ",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new RuntimeException( "hotel not found with id " + hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room= roomRepositoty.save(room);

        if (hotel.getActive()) {
            inventoryService.insitlizeRoomForAYear(room);
        }
        return modelMapper.map(room, RoomDto.class);
        }



    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("getting all rooms in hotel using hotelID :  {} ",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new RuntimeException( "hotel not found with id " + hotelId));

        return hotel.getRooms()
                .stream()
                .map((element)-> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("getting all rooms in hotel using RoomId :  {} ",roomId);
        Room room = roomRepositoty
                .findById(roomId)
                .orElseThrow(() -> new RuntimeException( "Room not found with id " + roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting all rooms in hotel using roomId :  {} ",roomId);
        Boolean exists = roomRepositoty.existsById(roomId);
         Room room = roomRepositoty
                .findById(roomId)
                .orElseThrow(() -> new RuntimeException( "Room not found with id " + roomId));

        inventoryService.deleteFutureInventory(room);
        roomRepositoty.deleteById(roomId);


    }
}
