package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;

@Data
public class ConfigBill {
    private int id;
    private ConnectionType connectionType;
    private String steps;
    private int startUnit;
    private int endUnit;
    private double perUnitAmount;
    private double flatAmount;
    private double offPickAmount;
    private double pickAmount;
}
