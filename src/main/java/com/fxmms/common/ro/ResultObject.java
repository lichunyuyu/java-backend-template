package com.fxmms.common.ro;

import java.util.Collection;

/**
 * 结果对象接口，主要用于service向Controller层返回响应数据集及其它信�?
 * 
 * 
 * @param <T>
 */
public interface ResultObject<T> {
	//方法 返回类型 collection
	Collection<T> getResults();

	void setResults(Collection<T> results);
	
	boolean isEmptyResult();
}
