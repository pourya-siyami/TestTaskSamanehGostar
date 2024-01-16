package com.samanehgostar.task.Service;

import com.samanehgostar.task.Model.Address;
import com.samanehgostar.task.Model.Customer;
import com.samanehgostar.task.Repositories.CustomerRepository;
import com.samanehgostar.task.dto.AddressDTO;
import com.samanehgostar.task.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerDTO = convertToDTO(customer);
        }
        return customerDTO;
    }

    @Override
    public List<Customer> findByFirstName(String name) {
        return customerRepository.findByFirstName(name);
    }

    @Override
    public List<Customer> findByLastName(String name) {
        return customerRepository.findByLastName(name);
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customerRepository.update(customer);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id: " + id + "not found"));
        customerRepository.delete(customer);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setNationalCode(customer.getNationalCode());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setBirthdayDate(customer.getBirthdayDate());
        customerDTO.setRegisterDate(customer.getRegisterDate());
        customerDTO.setFatherName(customer.getFatherName());
        customerDTO.setGender(customer.getGender());
        if (customer.getAddressList() != null) {
            customerDTO.setAddressList(customer.getAddressList().stream()
                    .map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList()));
        }
        return customerDTO;
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setNationalCode(customerDTO.getNationalCode());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setBirthdayDate(customerDTO.getBirthdayDate());
        customer.setRegisterDate(customerDTO.getRegisterDate());
        customer.setFatherName(customerDTO.getFatherName());
        customer.setGender(customerDTO.getGender());
        if (customerDTO.getAddressList() != null) {
            customer.setAddressList(customerDTO.getAddressList().stream()
                    .map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList()));
        }
        return customer;
    }
}
