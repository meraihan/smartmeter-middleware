package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;
import java.util.Date;

@Data
public class Meter {
    private int id;
    private User user;
    private ConnectionType connectionType;
    private String meterNo;
    private String accountNo;
    private boolean isActive;
    private Status status;
    private Date registrationDate;

    

    public enum Status{
        PENDING, REJECTED, APPROVED
    }
}
