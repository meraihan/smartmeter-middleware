package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.MeterData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class MeterDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MeterData add(MeterData meterData) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meter_data")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("meter_id", meterData.getMeter().getId());
        parameterMap.put("current_meter_reading", meterData.getCurrentMeterReading());
        parameterMap.put("created_date", new Date());

        log.info("Meter Data Added With Parameter: {}", parameterMap);

        try {
            Number autoGenId = jdbcInsert.executeAndReturnKey(parameterMap);
            if (autoGenId != null) {
                meterData.setId(autoGenId.intValue());
                log.info("Meter Data Added With ID: {}", autoGenId);
                return meterData;
            } else {
                return meterData;
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            log.info("Meter Data Did not Added: {}", e.getMessage());
            return meterData;
        }
    }

    public MeterData findLatestInsert(int meterId) {
        String query = "SELECT * FROM meter_data WHERE meter_id = ? ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{meterId}, (rs, i) -> {
                MeterData meterData = new MeterData();
                meterData.setId(rs.getInt("id"));
                Meter meter = new Meter();
                meter.setId(rs.getInt("meter_id"));
                meterData.setMeter(meter);
                meterData.setCurrentMeterReading(rs.getInt("current_meter_reading"));
                meterData.setCreatedDate(rs.getDate("created_date"));
                return meterData;
            });
        } catch (DataAccessException e) {
            log.error("Meter Data query execution failed: {}. Error: {}", query, e.getMessage());
            return new MeterData();
        }
    }
}
