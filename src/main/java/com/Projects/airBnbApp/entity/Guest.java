package com.Projects.airBnbApp.entity;

import com.Projects.airBnbApp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name ;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @ManyToMany
    @JoinTable
    private Set<Booking> bookings;

}
