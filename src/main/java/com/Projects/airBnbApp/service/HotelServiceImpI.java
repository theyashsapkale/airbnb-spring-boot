package com.Projects.airBnbApp.service;

import com.Projects.airBnbApp.DTO.HotelDto;
import com.Projects.airBnbApp.entity.Hotel;
import com.Projects.airBnbApp.entity.Room;
import com.Projects.airBnbApp.exception.ResourceNotFoundException;
import com.Projects.airBnbApp.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpI implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("creating a new hotel with name{}: ", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotelDto.setActive(false);
        hotelRepository.save(hotel);
        log.info("created a new hotel with ID{}: ", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("getting the hotel with ID:  {} ", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("hotel not found with id " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("updating the hotel with ID:  {} ", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("updating not found with id " + id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("updating not found with id " + id));

        hotelRepository.deleteById(id);//Delete the future invention

        for (Room room: hotel.getRooms()) {
            inventoryService.deleteFutureInventory(room);
        }
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("activationg the hotel with ID:  {} ", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new RuntimeException("updating not found with id " + hotelId));
        hotel.setActive(true);

//    assuning only do it once
        for (Room room: hotel.getRooms()) {
            inventoryService.insitlizeRoomForAYear(room);
        }
    }
}
