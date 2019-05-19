package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.ConfigBill;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static junit.framework.TestCase.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConfigDao {

    @Autowired
    ConfigBillDao configBillDao;

    @Test
    public void testAllConfig(){
        List<ConfigBill> configList = configBillDao.findConfig();
        assertTrue(configList.size() >= 1);
        for (ConfigBill bill : configList){
            log.info("Steps :{}, Start Unit: {}, End Unit: {}, Per Unit Amount: {}", bill.getSteps(),
                    bill.getStartUnit(), bill.getEndUnit(), bill.getPerUnitAmount());
        }
    }
}
