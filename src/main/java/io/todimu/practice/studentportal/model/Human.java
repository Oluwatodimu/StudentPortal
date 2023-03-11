package io.todimu.practice.studentportal.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Human extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
