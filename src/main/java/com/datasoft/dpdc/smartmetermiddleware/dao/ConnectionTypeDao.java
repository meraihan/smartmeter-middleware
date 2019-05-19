package com.datasoft.dpdc.smartmetermiddleware.dao;

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

/**
 * Created by rayhan on 7/22/18.
 */
@Slf4j
@Repository
public class ConnectionTypeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ConnectionType> findAll() {
        String query = "SELECT * FROM connection_type";
        try {
            return jdbcTemplate.query(query, new ConnectionTypeRowMapper());
        } catch (DataAccessException e) {
            log.error("Connection Type query execution failed: {}. Error: {}", query, e.getMessage());
            return new ArrayList<>();
        }
    }

    public ConnectionType findById(int id) {
        String query = "SELECT * FROM connection_type WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, new ConnectionTypeRowMapper());
        } catch (DataAccessException e) {
            log.error("Connection Type query execution failed: {}. id: {}. Error: {}", query,id, e.getMessage());
            return new ConnectionType();
        }
    }

    class ConnectionTypeRowMapper implements RowMapper<ConnectionType> {

        @Override
        public ConnectionType mapRow(ResultSet rs, int i) throws SQLException {
            ConnectionType type = new ConnectionType();
            type.setId(rs.getInt("id"));
            type.setTypeName(ConnectionType.TypeName.valueOf(rs.getString("type_name")));
            return type;
        }
    }
}
