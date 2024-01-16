package com.samanehgostar.task.Controller;

import com.samanehgostar.task.Service.CustomerServiceImpl;
import com.samanehgostar.task.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllCustomers")
    public List<CustomerDTO> getAllCustomers() {
        logger.info("return all customers");
        return customerServiceImpl.findAll().stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getCustomer/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        logger.info("find customer by id");
        return customerServiceImpl.findById(id);
    }

    @GetMapping("/getCustomerByFirstName")
    public ResponseEntity<List<CustomerDTO>> findByFirstName(@RequestParam String name) {
        logger.info("find customers by first name");
        return new ResponseEntity<>(customerServiceImpl.findByFirstName(name).stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getCustomerByLastName")
    public ResponseEntity<List<CustomerDTO>> findByLastName(@RequestParam String name) {
        logger.info("find customers by last name");
        return new ResponseEntity<>(customerServiceImpl.findByLastName(name).stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("add customer method");
        customerServiceImpl.save(customerDTO);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("update customer method");
        customerServiceImpl.update(customerDTO);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        logger.info("delete customer method");
        customerServiceImpl.deleteById(id);
    }
}
