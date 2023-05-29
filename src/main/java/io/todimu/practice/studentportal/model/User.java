package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.todimu.practice.studentportal.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(name = "user_id")
    private UUID userId;

    @JsonIgnore
    @Column(name = "password_hash")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\d{13}$")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "activated")
    private boolean activated = false;

    @Column(name = "password_reset_date")
    private Instant passwordResetDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();
}
