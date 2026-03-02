package com.example.springproject.studentmanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequestDTO {

    @NotBlank(message = "House no. can't be empty")
    private String houseNo;

    @NotBlank(message = "City can't be empty")
    private String city;

    @NotBlank(message = "State can't be empty")
    private String state;

    @NotBlank(message = "Pincode can't be empty")
    @Min(6)
    @Max(6)
    @Pattern(regexp = "//6" , message = "Pincode must be at least ")
    private String pincode;

}
