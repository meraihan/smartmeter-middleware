package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.UserBillSummary;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserBillSummaryDao {

    @Autowired
    UserBillSummaryDao billSummaryDao;

    @Test
    public void testBillSummary(){
        UserBillSummary billSummary = billSummaryDao.findBillSummary(1);
        log.info("User Bill Summary: {}", billSummary);
    }
}
