package com.example.springproject.studentmanagement.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private String houseNo;
    private String city;
    private String state;
    private String pincode;
}
