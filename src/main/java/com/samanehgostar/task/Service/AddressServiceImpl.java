package com.samanehgostar.task.Service;

import com.samanehgostar.task.Model.Address;
import com.samanehgostar.task.Model.Customer;
import com.samanehgostar.task.Repositories.AddressRepository;
import com.samanehgostar.task.dto.AddressDTO;
import com.samanehgostar.task.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public AddressDTO findById(Long id) {
        AddressDTO addressDTO = new AddressDTO();
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            addressDTO = convertToDTO(address);
        }
        return addressDTO;
    }

    @Override
    public List<Address> findByCountry(String name) {
        return addressRepository.findByCountry(name);
    }

    @Override
    public List<Address> findByCity(String name) {
        return addressRepository.findByCity(name);
    }

    @Override
    public void save(AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        addressRepository.save(address);
    }

    @Override
    public void update(AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        addressRepository.update(address);
    }

    @Override
    public void deleteById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address with id: " + id + "not found"));
        addressRepository.delete(address);
    }

    private AddressDTO convertToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setBuildingNum(address.getBuildingNum());
        addressDTO.setCustomer(modelMapper.map(address.getCustomer(), CustomerDTO.class));
        return addressDTO;
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        Address address = new Address();

        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setCountry(addressDTO.getCountry());
        address.setBuildingNum(addressDTO.getBuildingNum());
        address.setCustomer(modelMapper.map(addressDTO.getCustomer(), Customer.class));
        return address;
    }
}
