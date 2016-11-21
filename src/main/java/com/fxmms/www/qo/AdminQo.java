package com.fxmms.www.qo;

import com.fxmms.common.ro.PageQo;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Created by mark on 16/11/3.
 */

public class AdminQo extends PageQo {
    private int isDelete;

    private String searchesParm;

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getSearchesParm() {
        return searchesParm;
    }

    public void setSearchesParm(String searchesParm) {
        this.searchesParm = searchesParm;
    }

    @Override
    public void add(Criteria criteria) {
        criteria.add(Restrictions.eqOrIsNull("isDelete", 0));
        if (searchesParm != null && !("").equals(searchesParm)) {
            criteria.add(Restrictions.or(Restrictions.like("userName", searchesParm, MatchMode.ANYWHERE),
                         Restrictions.or(Restrictions.like("role", searchesParm, MatchMode.ANYWHERE))));
        }
    }
}
