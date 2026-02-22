package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        return ResponseEntity.ok(addressService.createAddress(address));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        return ResponseEntity.ok(addressService.getAddress());
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @RequestBody Address address){
        return ResponseEntity.ok(addressService.updateAddress(addressId, address));
    }
}
