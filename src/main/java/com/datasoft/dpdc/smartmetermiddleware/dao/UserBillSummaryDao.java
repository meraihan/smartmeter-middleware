package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.UserBillSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class UserBillSummaryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserBillSummary findBillSummary(int meterId) {
        String query = "SELECT * FROM user_bill_summary WHERE meter_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{meterId}, new UserBillSummaryRowMapper());
        } catch (DataAccessException e) {
            log.error(" ");
            log.error("Bill Summary query execution failed: {}. Meter ID: {}. " +
                    " Error: {}", query, meterId, e.getMessage());
            return new UserBillSummary();
        }
    }

    public boolean update(UserBillSummary billSummary) {
        String query = "UPDATE user_bill_summary SET last_bill_meter_reading=COALESCE (?, last_bill_meter_reading), " +
                "last_bill_amount_tk = COALESCE (?, last_bill_amount_tk), last_bill_usage_unit = COALESCE (?, last_bill_usage_unit)," +
                " current_usage_unit = COALESCE (?, current_usage_unit)," +
                " current_usage_amount_tk = COALESCE (?, current_usage_amount_tk), " +
                " demand_rate = COALESCE (?, demand_rate) WHERE id = ?";
        try {
            return jdbcTemplate.update(query, billSummary.getLastBillMeterReading(), billSummary.getLastBillAmountTk(),
                    billSummary.getLastBillUsageUnit(), billSummary.getCurrentUsageUnit(),
                    billSummary.getCurrentUsageAmountTk(),billSummary.getDemandRate(), billSummary.getId()) == 1;
        } catch (DataAccessException e) {
            log.error("Update failed for object: {}. Error: {}", billSummary, e.getLocalizedMessage());
            return false;
        }
    }

    class UserBillSummaryRowMapper implements RowMapper<UserBillSummary> {

        @Override
        public UserBillSummary mapRow(ResultSet rs, int i) throws SQLException {
            UserBillSummary billSummary = new UserBillSummary();
            billSummary.setId(rs.getInt("id"));
            Meter meter = new Meter();
            meter.setId(rs.getInt("meter_id"));
            billSummary.setMeter(meter);
            billSummary.setLastBillDate(rs.getDate("last_bill_date"));
            billSummary.setLastBillMeterReading(rs.getInt("last_bill_meter_reading"));
            billSummary.setLastBillAmountTk(rs.getBigDecimal("last_bill_amount_tk"));
            billSummary.setLastBillUsageUnit(rs.getInt("last_bill_usage_unit"));
            billSummary.setCurrentUsageUnit(rs.getInt("current_usage_unit"));
            billSummary.setCurrentUsageAmountTk(rs.getBigDecimal("current_usage_amount_tk"));
            billSummary.setDemandRate(rs.getBigDecimal("demand_rate"));
            billSummary.setCreatedDate(rs.getDate("created_date"));
            return billSummary;
        }
    }
}
