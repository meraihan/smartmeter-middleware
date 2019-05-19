package com.datasoft.dpdc.smartmetermiddleware.dao;

import com.datasoft.dpdc.smartmetermiddleware.model.Address;
import com.datasoft.dpdc.smartmetermiddleware.model.Role;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import com.datasoft.dpdc.smartmetermiddleware.utils.Helper;
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
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User add(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("id", user.getId());
        parameterMap.put("name", user.getName());
        parameterMap.put("user_name", user.getUserName());
        parameterMap.put("password", Helper.bCryptEncoder.encode(user.getPassword()));
        parameterMap.put("role_id", user.getRole().getId());
        parameterMap.put("address_id", 1);
        parameterMap.put("registration_date", user.getRegistrationDate());

        log.info("User Added With Parameter: {}", parameterMap);

        try {
            Number autoGenId = jdbcInsert.executeAndReturnKey(parameterMap);
            if (autoGenId != null) {
                user.setId(autoGenId.intValue());
                log.info("User Added With ID: {}", autoGenId);
                return user;
            } else {
                return user;
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            log.info("User Data Did not Added: {}", e.getMessage());
            return user;
        }
    }

    public List<User> findAll() {
        String query = "SELECT user.*, address.* FROM user INNER JOIN address\n" +
                " ON user.address_id = address.id";
        try {
            return jdbcTemplate.query(query, new UserRowMapper());
        } catch (DataAccessException e) {
            log.error("User query execution failed: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }


    public User findUser(int userId) {
        String query = " SELECT user.*, address.* FROM user INNER JOIN address\n" +
                " ON user.address_id = address.id AND user.id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{userId}, new UserRowMapper());
        } catch (DataAccessException e) {
            log.error("User query execution failed: {}. User Id: {}.  Error: {}", query, userId, e.getMessage());
            return new User();
        }
    }

    public User findByUserName(String userName) {
        try {
            return jdbcTemplate.queryForObject("SELECT user.*, address.* FROM user INNER JOIN address\n" +
                            " ON user.address_id = address.id AND binary user_name = ?",
                    new Object[]{userName}, new UserRowMapper());
        } catch (DataAccessException e) {
            log.error("Finding Failed with User Name: {}. Error: {}.", userName, e.getMessage());
            return new User();
        }
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));
            Role role = new Role();
            role.setId(rs.getInt("role_id"));
            user.setRole(role);
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setHouse(rs.getString("house"));
            address.setRoad(rs.getString("road"));
            address.setBlock(rs.getString("block"));
            address.setSector(rs.getString("sector"));
            address.setDistrict(rs.getString("district"));
            address.setPoliceStation(rs.getString("police_station"));
            address.setPostOffice(rs.getString("post_office"));
            address.setZone(rs.getString("zone"));
            address.setZipCode(rs.getString("zip_code"));
            user.setAddress(address);
            user.setRegistrationDate(rs.getDate("registration_date"));
            return user;
        }
    }
}
