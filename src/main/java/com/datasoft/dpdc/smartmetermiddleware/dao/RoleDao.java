package com.datasoft.dpdc.smartmetermiddleware.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by rayhan on 7/23/18.
 */
@Repository
@Slf4j
public class RoleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String findRoleNameById(Integer roleId) {
        String query = "SELECT name FROM role WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{roleId}, String.class);
        } catch (DataAccessException e) {
            log.error("Getting Role Name Failed With Query: {}. Error: {}", query, e.getMessage());
            return "";
        }
    }
}
