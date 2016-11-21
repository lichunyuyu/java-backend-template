
package com.fxmms.common.qo;

import org.hibernate.Criteria;



/**
 * 
 * 
 * @author billy
 */
public interface QueryObject {
	
	void beforeBuildCriteria();

	Criteria afterBuildCriteria(Criteria cr);
}
