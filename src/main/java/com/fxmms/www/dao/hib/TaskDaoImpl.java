package com.fxmms.www.dao.hib;

import com.fxmms.common.dao.hib.HibernateTemplateDao;
import com.fxmms.www.dao.TaskDao;
import com.fxmms.www.domain.Task;

/**
 * Created by mark on 16/11/8.
 * @usage 使用适配器模式，将common层中定义的公共访问数据库方法实现嫁接到TaskDao类的接口中。
 */
public class TaskDaoImpl extends HibernateTemplateDao<Task> implements TaskDao {
    @Override
    protected Class<?> getEntityClass() {
        return Task.class;
    }

}
