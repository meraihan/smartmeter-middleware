package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.MeterData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserBillSummaryService {

    @Autowired
    UserBillSummaryService billSummaryService;

    @Test
    public void testCalculateBillSummary(){
        MeterData meterData = new MeterData();
        meterData.setId(1);
        Meter meter = new Meter();
        meter.setId(2);
        meterData.setMeter(meter);
        meterData.setCurrentMeterReading(250);
        meterData.setCreatedDate(new Date());
        boolean isUpdate = billSummaryService.updateUserBillSummary(meterData);
        log.info("Is Update Bill Summary: {} ", isUpdate);
    }
}
