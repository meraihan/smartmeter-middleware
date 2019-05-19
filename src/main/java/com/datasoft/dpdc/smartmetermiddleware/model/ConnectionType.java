package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;

@Data
public class ConnectionType {
    private int id;
    private TypeName typeName;

    public enum TypeName{
        HOUSEHOLD, COMMERCIAL, INDUSTRY
    }
}
