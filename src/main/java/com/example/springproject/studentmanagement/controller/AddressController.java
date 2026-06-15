package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.dto.AddressRequestDTO;
import com.example.springproject.studentmanagement.dto.AddressResponseDTO;
import com.example.springproject.studentmanagement.service.AddressService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressRequestDTO addressDTO){
         return ResponseEntity.ok(addressService.createAddress(addressDTO));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAddresses(){
        return ResponseEntity.ok(addressService.getAddress());
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressRequestDTO addressRequestDTO){
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressRequestDTO));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }
}
