package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Student;
import lombok.Data;

@Data
public class AddressResponseDTO {
    private String houseNo;
    private String city;
    private String state;
    private String pincode;
}
