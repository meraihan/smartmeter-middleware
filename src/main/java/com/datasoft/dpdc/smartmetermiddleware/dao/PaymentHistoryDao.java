package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.Address;
import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.PaymentHistory;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 10/24/18.
 */
@Slf4j
@Repository
public class PaymentHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<PaymentHistory> findPaymentHistory(Integer meterId) {
        String query = "SELECT * FROM payment_history WHERE meter_id = ?";
        try {
            return jdbcTemplate.query(query, new Object[]{meterId}, new PaymentHistoryRowMapper());
        } catch (DataAccessException e) {
            log.error("Getting Role Name Failed With Query: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }

    class PaymentHistoryRowMapper implements RowMapper<PaymentHistory> {
        @Override
        public PaymentHistory mapRow(ResultSet rs, int i) throws SQLException {
            PaymentHistory paymentHistory = new PaymentHistory();
            paymentHistory.setId(rs.getInt("id"));
            User user = new User();
            user.setId(rs.getInt("user_id"));
            paymentHistory.setUser(user);
            Meter meter = new Meter();
            meter.setId(rs.getInt("meter_id"));
            paymentHistory.setMeter(meter);
            paymentHistory.setStatus(PaymentHistory.Status.valueOf(rs.getString("status")));
            paymentHistory.setTranId(rs.getInt("trans_id"));
            paymentHistory.setMonth(PaymentHistory.Month.valueOf(rs.getString("month")));
            paymentHistory.setYear(rs.getInt("year"));
            paymentHistory.setPayDate(rs.getDate("pay_date"));
            Address address = new Address();
            address.setId(rs.getInt("address_id"));
            paymentHistory.setAddress(address);
            return paymentHistory;
        }
    }
}
