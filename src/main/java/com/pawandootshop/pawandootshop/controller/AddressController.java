package com.pawandootshop.pawandootshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pawandootshop.pawandootshop.model.Address;
import com.pawandootshop.pawandootshop.service.AddressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.saveOrUpdateAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        Optional<Address> optionalAddress = addressService.getAddressById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            // Update the fields you want to update
            address.setStreetAddress(addressDetails.getStreetAddress());
            address.setCity(addressDetails.getCity());
            // Set other fields as needed
            return addressService.saveOrUpdateAddress(address);
        } else {
            // Handle address not found
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
    
}
