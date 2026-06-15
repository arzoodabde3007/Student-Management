package com.example.springproject.studentmanagement.mappers;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.dto.AddressRequestDTO;
import com.example.springproject.studentmanagement.dto.AddressResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public  Address addressRequestDTOToEntity(AddressRequestDTO addressRequestDTO){
        Address address = new Address();
        address.setHouseNo(addressRequestDTO.getHouseNo());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setPincode(addressRequestDTO.getPincode());
        return address;
    }

    public AddressResponseDTO entityToAddressResponseDTO(Address address){
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setHouseNo(address.getHouseNo());
        responseDTO.setCity(address.getCity());
        responseDTO.setState(address.getState());
        responseDTO.setPincode(address.getPincode());
        return responseDTO;
    }
}
