package com.samanehgostar.task.Service;

import com.samanehgostar.task.Model.Customer;
import com.samanehgostar.task.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    CustomerDTO findById(Long id);

    List<Customer> findByFirstName(String name);

    List<Customer> findByLastName(String name);

    void save(CustomerDTO customerDTO);

    void update(CustomerDTO customerDTO);

    void deleteById(Long id);
}
