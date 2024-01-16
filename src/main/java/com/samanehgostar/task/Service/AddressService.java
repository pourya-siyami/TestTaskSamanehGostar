package com.samanehgostar.task.Service;

import com.samanehgostar.task.Model.Address;
import com.samanehgostar.task.Model.Customer;
import com.samanehgostar.task.dto.AddressDTO;
import com.samanehgostar.task.dto.CustomerDTO;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    AddressDTO findById(Long id);

    List<Address> findByCountry(String name);

    List<Address> findByCity(String name);

    void save(AddressDTO addressDTO);

    void update(AddressDTO addressDTO);

    void deleteById(Long id);
}
