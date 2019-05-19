package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String name;
    private String userName;
    private String password;
    private Address address;
    private Role role;
    private Date registrationDate;
}
