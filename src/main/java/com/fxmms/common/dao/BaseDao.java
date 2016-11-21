package com.fxmms.common.dao;

import com.fxmms.common.ro.Dto;
import com.fxmms.common.ro.DtoResultWithPageInfo;
import com.fxmms.common.ro.PageQo;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
 * @param <T>
 * @usage  公共数据库操作接口
 */
@Repository
public interface BaseDao<T> {

	/**
	 *
	 * 
	 * @param id
	 *            ʵ��id
	 * @return T
	 */
	public T getById(Serializable id);

	/**
	 * ͨ��id��ȡָ��id
	 * 
	 * @param id
	 *            ʵ��id
	 * @return T
	 */
	public T load(Serializable id);

	/**
	 * ͨ��һ������ѯָ��ʵ��
	 * 
	 * @param columnName
	 *            ��ѯ�ֶ���
	 * @param value
	 *            ��ѯ�ֶ�ֵ
	 * @return
	 */
	public T getByUniqueKey(String columnName, Object value);

	/**
	 * ͨ���������������������ѯ����Ϊeq����ѯָ��ʵ��
	 * 
	 * @param nameValuePairs
	 *            ��ѯ�����ֶ��� /ֵ map����
	 * @return T
	 */
	public T getUniqueResult(Map<String, Object> nameValuePairs);

	/**
	 * ͨ��һ������ѯָ��ʵ��List
	 * 
	 * @param columnName
	 *            ��ѯ�ֶ���
	 * @param value
	 *            ��ѯ�ֶ�ֵ
	 * @param sort
	 *            �����ֶ���
	 * @param order
	 *            �����ǽ���asc/desc��
	 * @return List<T>
	 */
	public List<T> getListByColumn(String columnName, Object value,
                                   String sort, String order);

	public List<T> getListByColumn(String columnName, Object value);

	/**
	 * ͨ���������������������ѯ����Ϊeq����ѯָ��ʵ��List
	 * 
	 * @param nameValuePairs
	 *            ��ѯ�����ֶ��� /ֵ map����
	 * @param sort
	 *            �����ֶ���
	 * @param order
	 *            �����ǽ���asc/desc��
	 * @return List<T>
	 */
	public List<T> getListByColumns(Map<String, Object> nameValuePairs,
                                    String sort, String order);

	public List<T> getListByColumns(Map<String, Object> nameValuePairs);

	/**
	 * ȫȡ����ָ��ʵ��List
	 * 
	 * @return List<T>
	 */
	public List<T> getAll();

	/**
	 * ����ʵ��
	 * 
	 * @param t
	 * @return Serializable ����id
	 */
	public Serializable save(T t);

	/**
	 * ����ʵ��
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * �õ�QBC�ӿ�
	 * @return
	 */
	public Criteria createCriteria();
	
	/**
	 * @param <E>
	 * @param <D>
	 * @param criteria
	 * @param pageNo
	 * @param pageSize
	 * @param dtoClazz
	 * @return
	 */
	public <E, D extends Dto> DtoResultWithPageInfo<D> queryPageListByCriteria(
            Criteria criteria, int pageNo, int pageSize, Class<D> dtoClazz);
	

	/**
	 * @param <E>
	 * @param <D>
	 * @param criteria
	 * @param qo
	 * @param class1
	 * @return
	 */
	public <E, D extends Dto> DtoResultWithPageInfo<D> queryPageListByCriteriaWithQo(PageQo qo, Class<D> dtoClazz);


}
