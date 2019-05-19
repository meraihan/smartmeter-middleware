package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.PaymentHistoryDao;
import com.datasoft.dpdc.smartmetermiddleware.model.PaymentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rayhan on 10/24/18.
 */
@Service
public class PaymentHistoryService {

    @Autowired
    PaymentHistoryDao paymentHistoryDao;

    public List<PaymentHistory> findPaymentHistory(int meterId) {

        List<PaymentHistory> paymentHistoryList = paymentHistoryDao.findPaymentHistory(meterId);
        return paymentHistoryList;
    }
}
