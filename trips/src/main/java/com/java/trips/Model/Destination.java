package com.java.trips.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Destination{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "total_amount")
    private double totalAmount;

    // Other order details...

    @ManyToOne
    @JoinColumn(name = "login_id")
    @JsonIgnore
    private Login login;

    // Constructors, getters, setters
}
