package com.surendiran.ecom.app.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private AddressDTO addressDTO;
}
