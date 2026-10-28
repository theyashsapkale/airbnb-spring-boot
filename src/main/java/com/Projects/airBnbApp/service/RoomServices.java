package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.DTO.RoomDto;

import java.util.List;

public interface RoomServices {


    RoomDto createNewRoom(Long hotelId , RoomDto roomDto);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);
    void deleteRoomById(Long roomId);
}
