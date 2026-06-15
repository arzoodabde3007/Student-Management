package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.mappers.AddressMapper;
import com.example.springproject.studentmanagement.dto.AddressRequestDTO;
import com.example.springproject.studentmanagement.dto.AddressResponseDTO;
import com.example.springproject.studentmanagement.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public AddressResponseDTO createAddress(AddressRequestDTO addressDTO){
        Address address = addressMapper.addressRequestDTOToEntity(addressDTO);
        return addressMapper.entityToAddressResponseDTO(addressRepository.save(address));
    }

    public List<AddressResponseDTO> getAddress(){
        List<Address> addresses =  addressRepository.findAll();
        List<AddressResponseDTO> responseAddresses =  addresses.stream()
                .map(addressMapper::entityToAddressResponseDTO)
                .toList();

        return responseAddresses;

    }

    public AddressResponseDTO getAddressById(Long addressId){
        Address address =  addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new RuntimeException("Address not present")
                );
        return addressMapper.entityToAddressResponseDTO(address);
    }

    public AddressResponseDTO updateAddress(Long addressId, AddressRequestDTO updatedAddress){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new RuntimeException("Address not found")
                );

        address.setHouseNo(updatedAddress.getHouseNo());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setPincode(updatedAddress.getPincode());

        Address savedAddress =  addressRepository.save(address);

        return addressMapper.entityToAddressResponseDTO(savedAddress);
    }
}
