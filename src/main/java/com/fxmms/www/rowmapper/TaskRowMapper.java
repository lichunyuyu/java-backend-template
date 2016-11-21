package com.fxmms.www.rowmapper;

import com.fxmms.www.domain.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mark on 16/11/10.
 * @usage 对应jdbcTemplate 查询task任务结果
 */
public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                     Task task = new Task();
                     task.setId(rs.getInt("id"));
                     return task;
    }
}
