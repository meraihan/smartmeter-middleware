package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.ConfigBillDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.ConnectionTypeDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.MeterDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.UserBillSummaryDao;
import com.datasoft.dpdc.smartmetermiddleware.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBillSummaryService {
    @Autowired
    UserBillSummaryDao userBillSummaryDao;
    @Autowired
    ConfigBillDao configBillDao;
    @Autowired
    MeterDao meterDao;
    @Autowired
    ConnectionTypeDao typeDao;

    public boolean updateUserBillSummary(MeterData meterData) {

        UserBillSummary billSummary = userBillSummaryDao.findBillSummary(meterData.getMeter().getId());

        UserBillSummary newBillSummary = new UserBillSummary();
        int currentUsageUnit = (meterData.getCurrentMeterReading() - billSummary.getLastBillMeterReading());
        newBillSummary.setId(billSummary.getId());
        newBillSummary.setMeter(billSummary.getMeter());
        newBillSummary.setLastBillDate(billSummary.getLastBillDate());
        newBillSummary.setLastBillUsageUnit(billSummary.getCurrentUsageUnit());
        newBillSummary.setLastBillAmountTk(billSummary.getCurrentUsageAmountTk());

        double perUnitAmount = calculatePerUnitBill(currentUsageUnit);
        BigDecimal currentUsageAmount = BigDecimal.valueOf(currentUsageUnit * perUnitAmount);

        newBillSummary.setCurrentUsageUnit(currentUsageUnit);
        newBillSummary.setCurrentUsageAmountTk(currentUsageAmount);
        newBillSummary.setLastBillMeterReading(meterData.getCurrentMeterReading());

        return userBillSummaryDao.update(newBillSummary);
    }

    public UserBillSummary getBillSummary(int meterId) {
        return userBillSummaryDao.findBillSummary(meterId);
    }

    public double calculatePerUnitBill(int currentUsageUnit){
        List<ConfigBill> billList = configBillDao.findConfig();
        double perUnitAmount = 0.0;
        for  (ConfigBill bill : billList){
            if(currentUsageUnit >= bill.getStartUnit() && currentUsageUnit <= bill.getEndUnit()){
                perUnitAmount = bill.getPerUnitAmount();
            }
        }
        return perUnitAmount;
    }
}
