package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.ConnectionType;
import com.datasoft.dpdc.smartmetermiddleware.model.Meter;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Repository
public class MeterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Meter add(Meter meter) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meter")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("user_id", 1);
        parameterMap.put("connection_type_id", 2);
        parameterMap.put("meter_no", meter.getMeterNo());
        parameterMap.put("account_no", meter.getAccountNo());
        parameterMap.put("registration_date", new Date());

        log.info("Meter Added With Parameter: {}", parameterMap);

        try {
            Number autoGenId = jdbcInsert.executeAndReturnKey(parameterMap);
            if (autoGenId != null) {
                meter.setId(autoGenId.intValue());
                log.info("Meter Added With ID: {}", autoGenId);
                return meter;
            } else {
                return meter;
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            log.info("Meter Data Did not Added: {}", e.getMessage());
            return meter;
        }
    }


    public List<Meter> findAll() {
        String query = "SELECT * FROM meter";
        try {
            return jdbcTemplate.query(query, new MeterRowMapper());
        } catch (DataAccessException e) {
            log.error("Meter query execution failed: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Meter> findAllMeterList() {
        String query = "SELECT u.name, ct.type_name, m.id, m.meter_no, m.account_no, m.is_active, m.status," +
                " m.registration_date FROM \n" +
                "meter m JOIN user u  ON m.user_id = u.id \n" +
                "JOIN connection_type ct ON m.connection_type_id = ct.id ";
        try {
            return jdbcTemplate.query(query, new RowMapper<Meter>() {
                @Override
                public Meter mapRow(ResultSet rs, int i) throws SQLException {
                    Meter meter = new Meter();
                    meter.setId(rs.getInt("id"));
                    User user = new User();
                    user.setUserName(rs.getString("name"));
                    meter.setUser(user);
                    ConnectionType type = new ConnectionType();
                    type.setTypeName(ConnectionType.TypeName.valueOf(rs.getString("type_name")));
                    meter.setConnectionType(type);
                    meter.setMeterNo(rs.getString("meter_no"));
                    meter.setAccountNo(rs.getString("account_no"));
                    meter.setActive(rs.getBoolean("is_active"));
//                    meter.setStatus(Meter.Status.valueOf(rs.getString("status")));
                    meter.setRegistrationDate(rs.getDate("registration_date"));
                    return meter;
                }
            });
        } catch (DataAccessException e) {
            log.error("Meter query execution failed: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }

    public Meter findMeter(String meterModel) {
        String query = "SELECT * FROM meter WHERE meter_no = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{meterModel}, new MeterRowMapper());
        } catch (DataAccessException e) {
            log.error("Meter query execution failed: {}. meter no: {}.  Error: {}",
                    query, meterModel, e.getMessage());
            return new Meter();
        }
    }

    public Meter findById(int id) {
        String query = "SELECT * FROM meter WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, new MeterRowMapper());
        } catch (DataAccessException e) {
            log.error("Meter query execution failed: {}. Error: {}", query, e.getMessage());
            return new Meter();
        }
    }

    public List<Meter> findByUserId(Integer userId) {
        String query = "SELECT type_name, meter_no FROM meter \n" +
                "INNER JOIN connection_type ON meter.connection_type_id = connection_type.id WHERE user_id = ?";
        try {
            return jdbcTemplate.query(query, new Object[]{userId}, new RowMapper<Meter>() {
                @Override
                public Meter mapRow(ResultSet rs, int i) throws SQLException {
                    Meter meter = new Meter();
                    meter.setMeterNo(rs.getString("meter_no"));
                    ConnectionType type = new ConnectionType();
                    type.setTypeName(ConnectionType.TypeName.valueOf(rs.getString("type_name")));
                    meter.setConnectionType(type);
                    return meter;
                }
            });
        } catch (DataAccessException e) {
            log.error("Getting Role Name Failed With Query: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }

    class MeterRowMapper implements RowMapper<Meter> {

        @Override
        public Meter mapRow(ResultSet rs, int i) throws SQLException {
            Meter meter = new Meter();
            meter.setId(rs.getInt("id"));
            User user = new User();
            user.setId(rs.getInt("user_id"));
            meter.setUser(user);
            ConnectionType type = new ConnectionType();
            type.setId(rs.getInt("connection_type_id"));
            meter.setConnectionType(type);
            meter.setActive(rs.getBoolean("is_active"));
            meter.setStatus(Meter.Status.valueOf(rs.getString("status")));
            meter.setMeterNo(rs.getString("meter_no"));
            meter.setAccountNo(rs.getString("account_no"));
            meter.setRegistrationDate(rs.getDate("registration_date"));
            return meter;
        }
    }
}
