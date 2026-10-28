package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.DTO.HotelDto;
import com.Projects.airBnbApp.entity.Hotel;

public interface HotelService   {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById( Long id , HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long hotelId);
}
