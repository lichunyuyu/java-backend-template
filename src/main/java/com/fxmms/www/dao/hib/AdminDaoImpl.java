package com.fxmms.www.dao.hib;

import com.fxmms.common.dao.hib.HibernateTemplateDao;
import com.fxmms.www.dao.AdminDao;
import com.fxmms.www.domain.Admin;

/**
 * Created by mark on 16/11/2.
 * @usage 使用适配器模式，将common层中定义的公共访问数据库方法实现嫁接到Admin类的接口中。
 */
public class AdminDaoImpl extends HibernateTemplateDao<Admin> implements AdminDao {

    @Override
    protected Class<?> getEntityClass() {
        // TODO Auto-generated method stub
        return Admin.class;
    }
}