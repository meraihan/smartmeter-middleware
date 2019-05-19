package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;
import java.util.Date;

@Data
public class MeterData {
    private int id;
    private Meter meter;
    private int currentMeterReading;
    private Date createdDate;
}
