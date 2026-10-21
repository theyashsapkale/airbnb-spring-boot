package com.Projects.airBnbApp.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
 public class HotelContectInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;
}
