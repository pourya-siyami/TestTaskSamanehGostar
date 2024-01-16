package com.samanehgostar.task.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerDTO {
    public CustomerDTO() {
    }

    private Long customerId;
    private Long nationalCode;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
    private LocalDate registerDate;
    private String fatherName;
    private String gender;
    private List<AddressDTO> addressList;
}
