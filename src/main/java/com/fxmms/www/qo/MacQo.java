package com.fxmms.www.qo;

import com.fxmms.common.ro.PageQo;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by mark on 16/11/7.
 * Mac地址录入状态查询组件类
 */
public class MacQo extends PageQo {
    private String macStatus;
    private String searchesParm;  //模糊查询参数
    private List<Integer> adminIdList;

    public String getMacStatus() {
        return macStatus;
    }

    public void setMacStatus(String macStatus) {
        this.macStatus = macStatus;
    }

    public String getSearchesParm() {
        return searchesParm;
    }

    public void setSearchesParm(String searchesParm) {
        this.searchesParm = searchesParm;
    }

    public List<Integer> getAdminIdList() {
        return adminIdList;
    }

    public void setAdminIdList(List<Integer> adminIdList) {
        this.adminIdList = adminIdList;
    }

    @Override
    public void add(Criteria criteria) {
        criteria.addOrder(Order.desc("date"));
        if (macStatus != null && !("".equals(macStatus))) {
            if ("init".equals(macStatus)) {
                criteria.add(Restrictions.eqOrIsNull("status", 0));
            }
            if ("deliver".equals(macStatus)) {
                criteria.add(Restrictions.eqOrIsNull("status", 1));
            }
            if ("success".equals(macStatus)) {
                criteria.add(Restrictions.eqOrIsNull("status", 2));
            }
            if ("failed".equals(macStatus)) {
                criteria.add(Restrictions.eqOrIsNull("status", 3));
            }
        }
        if (searchesParm != null && !("").equals(searchesParm)) {
            //java.sql.Date date = DateUtil.changeDateStrToSqlDate(searchesParm);
            if (this.getAdminIdList().size() != 0) {
                //criteria.add(Restrictions.or(Restrictions.like("macAddr", searchesParm, MatchMode.ANYWHERE),
                criteria.add(Restrictions.or(Restrictions.in("admin.id", this.getAdminIdList())));
                //criteria.add(Restrictions.or(Restrictions.like("to_char(date,'yyyy-MM-dd')",searchesParm)));
            } else {
                criteria.add(Restrictions.or(Restrictions.like("macAddr", searchesParm, MatchMode.ANYWHERE),
                        Restrictions.or(Restrictions.sqlRestriction(" {alias}.date like '%"+searchesParm+"%'"))
                                ));
            }
        }
    }
}
