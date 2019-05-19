package com.datasoft.dpdc.smartmetermiddleware.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserBillSummary {
    private int id;
    private Meter meter;
    private Date lastBillDate;
    private int lastBillMeterReading;
    private BigDecimal lastBillAmountTk;
    private int lastBillUsageUnit;
    private int currentUsageUnit;
    private BigDecimal currentUsageAmountTk;
    private BigDecimal demandRate;
    private Date createdDate;
}
