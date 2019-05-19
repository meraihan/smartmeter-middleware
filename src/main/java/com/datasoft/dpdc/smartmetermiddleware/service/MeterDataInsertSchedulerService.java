package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.MeterDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.MeterDataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class MeterDataInsertSchedulerService {

    @Autowired
    MeterDao meterDao;
    @Autowired
    MeterDataDao meterDataDao;

    @Autowired
    MeterDataService meterDataService;

    SecureRandom random = new SecureRandom();

//    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
//    public void addRandomMeterData() {
//        meterDao.findAll().parallelStream().forEach(meter -> {
//            MeterData meterData = meterDataDao.findLatestInsert(meter.getId());
//            if(meterData.getId() == 0) {
//                // No data exist for the current meter
//                meterData.setMeter(meter);
//                meterData.setCurrentMeterReading(random.nextInt(1000));
//                boolean isAdded = meterDataService.addMeterData(meterData);
//                log.info("Inserted meter data = {}", isAdded);
//            } else {
//                // Meter data exist
//                meterData.setCurrentMeterReading(meterData.getCurrentMeterReading() + random.nextInt(1000));
//                boolean isAdded = meterDataService.addMeterData(meterData);
//                log.info("Inserted meter data = {}", isAdded);
//            }
//        });
//    }
}
