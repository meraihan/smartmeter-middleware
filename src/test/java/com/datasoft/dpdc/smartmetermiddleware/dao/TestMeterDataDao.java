package com.datasoft.dpdc.smartmetermiddleware.dao;

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
public class TestMeterDataDao {

    @Autowired
    MeterDataDao meterDataDao;

    @Test
    public void testMeterDataAdd(){
        MeterData meterData = new MeterData();
        Meter meter = new Meter();
        meter.setId(1);
        meterData.setMeter(meter);
        meterData.setCurrentMeterReading(250);
        meterData.setCreatedDate(new Date());
        log.info("Added Meter Data:{} ", meterDataDao.add(meterData));
    }

    @Test
    public void testFindLatestMeterData(){
        MeterData meterData = meterDataDao.findLatestInsert(1);
        log.info("Latest Meter Data: {}", meterData);
    }
}
