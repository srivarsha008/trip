package com.java.trips.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Profile {
    @Id
    private int id;

    @Column(name="full_name")
    private String fullName;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private Login login;

    // Constructors, getters, setters
}