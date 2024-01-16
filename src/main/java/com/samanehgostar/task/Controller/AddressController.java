package com.samanehgostar.task.Controller;

import com.samanehgostar.task.Model.Address;
import com.samanehgostar.task.Service.AddressServiceImpl;
import com.samanehgostar.task.dto.AddressDTO;
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
@RequestMapping("addresses")
public class AddressController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllAddresses")
    public List<AddressDTO> getAllAddresses() {
        logger.info("return all addresses");
        return addressServiceImpl.findAll().stream().map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getAddress/{id}")
    public AddressDTO getAddress(@PathVariable Long id) {
        return addressServiceImpl.findById(id);
    }

    @GetMapping("/getAddressByCountry")
    public ResponseEntity<List<AddressDTO>> getAddressByCountry(@RequestParam String name) {
        return new ResponseEntity<>(addressServiceImpl.findByCountry(name).stream()
                .map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getAddressByCity")
    public ResponseEntity<List<AddressDTO>> findByCity(@RequestParam String name) {
        return new ResponseEntity<>(addressServiceImpl.findByCity(name).stream()
                .map(address -> modelMapper.map(address, AddressDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public void addAddress(@RequestBody AddressDTO addressDTO) {
        addressServiceImpl.save(addressDTO);
    }

    @PutMapping("/updateAddress")
    public void updateAddress(@RequestBody AddressDTO addressDTO) {
        addressServiceImpl.update(addressDTO);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressServiceImpl.deleteById(id);
    }
}
