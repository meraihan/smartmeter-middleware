package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;

@Data
public class Role {
    private int id;
    private Name  name;

    public enum Name{
        ADMIN, USER
    }
}
