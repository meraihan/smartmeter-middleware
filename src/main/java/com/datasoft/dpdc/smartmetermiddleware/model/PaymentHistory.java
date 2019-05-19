package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;
import java.util.Date;

/**
 * Created by rayhan on 10/22/18.
 */
@Data
public class PaymentHistory {
    private int id;
    private User user;
    private Meter meter;
    private Status status;
    private int tranId;
    private Month month;
    private int year;
    private Date payDate;
    private Address address;

    public enum Status {
        PENDING, SUCCESS, FAILED
    }

    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }
}
