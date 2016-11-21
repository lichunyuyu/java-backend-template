package com.fxmms.www.service;

import com.fxmms.common.ro.ControllerResult;
import com.fxmms.common.ro.DtoResultWithPageInfo;
import com.fxmms.www.dao.AdminDao;
import com.fxmms.www.domain.Admin;
import com.fxmms.www.dto.AdminDto;
import com.fxmms.www.qo.AdminQo;
import com.fxmms.www.rowmapper.AdminRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 16/11/2.
 *
 * @usage 对管理员操作的业务逻辑封装类
 */
@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertAdmin(Admin admin) {
        adminDao.save(admin);
    }

    /**
     * @param adminQo
     * @return
     * @usage 获取管理员列表
     */
    @Transactional
    public ControllerResult getAdminList(AdminQo adminQo) {
        DtoResultWithPageInfo<AdminDto> info = adminDao.queryPageListByCriteriaWithQo(adminQo, AdminDto.class);
        return ControllerResult.valueOf(ControllerResult.SUCCESS, "获取管理员列表成功", info);
    }

    /**
     * @param searchParam
     * @return 根据模糊查询参数获取管理员列表
     */
    @Transactional
    public List<Integer> getAdminIdList(String searchParam) {
        String querySql = "SELECT * FROM mms_admin  WHERE userName like '%" + searchParam + "%'";
        List<Admin> adminList = jdbcTemplate.query(querySql, new AdminRowMapper());
        List<Integer> adminIdList = new ArrayList<>();
        for (Admin admin : adminList) {
            adminIdList.add(admin.getId());
        }
        return adminIdList;
    }
}
