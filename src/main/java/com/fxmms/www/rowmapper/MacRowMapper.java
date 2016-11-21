package com.fxmms.www.rowmapper;

import com.fxmms.www.domain.Mac;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mark on 16/11/10.
 * @usage 对应JdbcTemplate 查询结果集
 */
public class MacRowMapper implements RowMapper<Mac> {
    @Override
    public Mac mapRow(ResultSet rs, int rowNum) throws SQLException {
        Mac mac = new Mac();
        mac.setDownLoadId(rs.getString("downLoadId"));
        return mac;
    }
}
