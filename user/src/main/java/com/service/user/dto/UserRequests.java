package com.app.ecommerce.dto;

import lombok.Data;

@Data
public class UserRequests {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
