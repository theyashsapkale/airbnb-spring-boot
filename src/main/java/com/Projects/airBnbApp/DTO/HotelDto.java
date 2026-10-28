package com.Projects.airBnbApp.DTO;

import com.Projects.airBnbApp.entity.HotelContactInfo;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
