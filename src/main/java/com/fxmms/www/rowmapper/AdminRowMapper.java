package com.fxmms.www.rowmapper;

import com.fxmms.www.domain.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mark on 16/11/14.
 */
public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id"));
        return admin;
    }
}
