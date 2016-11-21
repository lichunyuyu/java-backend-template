package com.fxmms.www.dao.hib;

import com.fxmms.common.dao.hib.HibernateTemplateDao;
import com.fxmms.www.dao.MacDao;
import com.fxmms.www.domain.Mac;

/**
 * Created by mark on 16/11/7.
 * @usage 使用适配器模式，将common层中定义的公共访问数据库方法实现嫁接到Mac类的接口中。
 */
public class MacDaoImpl extends HibernateTemplateDao<Mac> implements MacDao {
    @Override
    protected Class<?> getEntityClass() {
        return Mac.class;
    }
}
