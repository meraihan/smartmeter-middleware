package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;

@Data
public class Address {
    private int id;
    private String house;
    private String road;
    private String block;
    private String sector;
    private String district;
    private String policeStation;
    private String postOffice;
    private String zone;
    private String zipCode;
}
