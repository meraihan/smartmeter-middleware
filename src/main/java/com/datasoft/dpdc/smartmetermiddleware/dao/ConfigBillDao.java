package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.ConfigBill;
import com.datasoft.dpdc.smartmetermiddleware.model.ConnectionType;
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

@Slf4j
@Repository
public class ConfigBillDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ConfigBill> findConfig() {
        String query = "SELECT * FROM config_bill";
        try {
            return jdbcTemplate.query(query, new RowMapper<ConfigBill>() {
                @Override
                public ConfigBill mapRow(ResultSet rs, int i) throws SQLException {
                    ConfigBill configBill = new ConfigBill();
                    configBill.setId(rs.getInt("id"));
                    ConnectionType type = new ConnectionType();
                    type.setId(rs.getInt("connection_type_id"));
                    configBill.setConnectionType(type);
                    configBill.setSteps(rs.getString("steps"));
                    configBill.setStartUnit(rs.getInt("start_unit"));
                    configBill.setEndUnit(rs.getInt("end_unit"));
                    configBill.setPerUnitAmount(rs.getDouble("per_unit_amount"));
                    configBill.setFlatAmount(rs.getDouble("flat_amount"));
                    configBill.setOffPickAmount(rs.getDouble("off_pick_amount"));
                    configBill.setPickAmount(rs.getDouble("pick_amount"));
                    return configBill;
                }
            });
        } catch (DataAccessException e) {
            log.error("Config bill query execution failed: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }
}
