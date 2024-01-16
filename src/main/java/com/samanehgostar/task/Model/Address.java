package com.samanehgostar.task.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String street;
    private String buildingNum;
    @ManyToOne
    @JoinColumn(name = "Customer_Id")
    private Customer customer;
}
