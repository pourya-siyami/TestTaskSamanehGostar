package com.samanehgostar.task.Model;

import com.samanehgostar.task.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(unique = true)
    private Long nationalCode;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
    private LocalDate registerDate;
    private String fatherName;
    private String gender;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<Address> addressList;
}
