package com.Projects.airBnbApp.Controller;

import com.Projects.airBnbApp.DTO.RoomDto;
import com.Projects.airBnbApp.service.RoomServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotel/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomsAdminController {

    private  final RoomServices roomServices;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId,
                                            @RequestBody RoomDto roomDto){
        RoomDto room =roomServices.createNewRoom(hotelId,roomDto);
        return new ResponseEntity<> (room,HttpStatus.CREATED);
       }

       @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomServices.getAllRoomsInHotel(hotelId));
       }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getAllRoomById(@PathVariable Long hotelId, @PathVariable Long roomId){
        return ResponseEntity.ok((RoomDto) roomServices.getAllRoomsInHotel(roomId));
    }
    @DeleteMapping()
    public  ResponseEntity<RoomDto> deleteRoomById(@PathVariable Long hotelId, @PathVariable Long roomId){
        roomServices.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
