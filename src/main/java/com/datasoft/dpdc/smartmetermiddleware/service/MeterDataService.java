package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.ConnectionTypeDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.MeterDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.MeterDataDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.UserDao;
import com.datasoft.dpdc.smartmetermiddleware.model.ConnectionType;
import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.MeterData;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterDataService {
    @Autowired
    MeterDataDao meterDataDao;
    @Autowired
    private MeterDao meterDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    UserBillSummaryService userBillSummaryService;
    @Autowired
    ConnectionTypeDao typeDao;

    public List<Meter> findAllMeterList() {
        List<Meter> meterList = meterDao.findAllMeterList();
        return meterList;
    }

    public boolean addMeterData(MeterData meterData) {
        meterData = meterDataDao.add(meterData);
        if(meterData.getId() > 0){
            return userBillSummaryService.updateUserBillSummary(meterData);
        }
        return false;
    }

    public boolean addMeter(Meter meter) {
        meter = meterDao.add(meter);
        if(meter.getId() > 0){
            return true;
        }
        return false;
    }

    public Meter findMeterData(String mobileNumber){
        Meter meter = meterDao.findMeter(mobileNumber);
        if(meter!=null) {
            User user = userDao.findUser(meter.getUser().getId());
            meter.setUser(user);
            ConnectionType type = typeDao.findById(meter.getConnectionType().getId());
            meter.setConnectionType(type);
        }
        return meter;
    }

    public List<Meter> findMeterName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int userId = userDao.findByUserName(auth.getName()).getId();
        List<Meter> meterList = meterDao.findByUserId(userId);
        return meterList;
    }

    public List<User> findAllUser(){
        return userDao.findAll();
    }

    public Meter findMeterById(int meterId) {
        return meterDao.findById(meterId);
    }
}
