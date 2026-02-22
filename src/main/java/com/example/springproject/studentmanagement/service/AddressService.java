package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    public List<Address> getAddress(){
        return addressRepository.findAll();
    }

    public Address getAddressById(Long addressId){
        return addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new RuntimeException("Address not present")
                );
    }

    public Address updateAddress(Long addressId, Address updatedAddress){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new RuntimeException("Address not found")
                );

        address.setHouseNo(updatedAddress.getHouseNo());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setPincode(updatedAddress.getPincode());

        return addressRepository.save(address);
    }



}
