package com.samanehgostar.task.dto;

import com.samanehgostar.task.Model.Customer;
import lombok.Data;

@Data
public class AddressDTO {
    public AddressDTO() {
    }

    private Long id;
    private String country;
    private String city;
    private String street;
    private String buildingNum;
    private CustomerDTO customer;
}
