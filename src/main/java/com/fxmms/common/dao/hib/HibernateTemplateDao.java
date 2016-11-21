package com.fxmms.common.dao.hib;

import com.fxmms.common.dao.BaseDao;
import com.fxmms.common.ro.Dto;
import com.fxmms.common.ro.DtoResultWithPageInfo;
import com.fxmms.common.ro.PageInfo;
import com.fxmms.common.ro.PageQo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <T>
 * @usage 应用数据访问的灵魂，抽象出各种模型类进行数据库访问的公共操作。
 *        主要使用到QBC动态查询。主要思想是利用反射。
 */
@Repository
public abstract class HibernateTemplateDao<T> implements BaseDao<T> {
	protected static final Log log = LogFactory
			.getLog(HibernateTemplateDao.class);
    //通过反射，可以实现对不同类对应的数据表的操作
	protected abstract Class<?> getEntityClass();

	
	protected SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session openNewSession() {
		return sessionFactory.openSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(Serializable id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getByUniqueKey(String columnName, Object value) {
		return (T) getSession().createCriteria(getEntityClass())
				.add(Restrictions.eq(columnName, value)).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getListByColumn(String columnName, Object value,String sort,String order) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(columnName, value));
		if(StringUtils.hasText(sort) && StringUtils.hasText(order)){
			if("asc".equals(order)){
				criteria.addOrder(Order.asc(sort));
			}else if("desc".equals(order)){
				criteria.addOrder(Order.desc(sort));
			}			
		}
		List<T> list = criteria.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getListByColumn(String columnName, Object value) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(columnName, value));
		List<T> list = criteria.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getListByColumns(Map<String, Object> nameValuePairs,String sort,String order){
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Map.Entry<String, Object> entry : nameValuePairs.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		if(StringUtils.hasText(sort) && StringUtils.hasText(order)){
			if("asc".equals(order)){
				criteria.addOrder(Order.asc(sort));
			}else if("desc".equals(order)){
				criteria.addOrder(Order.desc(sort));
			}			
		}
		List<T> list = criteria.list();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getListByColumns(Map<String, Object> nameValuePairs){
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Map.Entry<String, Object> entry : nameValuePairs.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<T> list = criteria.list();
		return list;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSession().createCriteria(getEntityClass()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getUniqueResult(Map<String, Object> nameValuePairs) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Map.Entry<String, Object> entry : nameValuePairs.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return (T) criteria.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T load(Serializable id){
		return (T) getSession().load(getEntityClass(), id);
	}
	
	@Override
	public Serializable save(T t) {
		return getSession().save(t);
	}

	@Override
	public void update(T t) {
		Session session = this.getSession();
		session.update(t);
		//强制刷新缓存中数据至数据库中，防止大批量数据更新之后出现脏数据
		session.flush();
	}

	@Override
	public void delete(T t) {
		this.getSession().delete(t);
	}
	
	/**
	 * ����QO ���� DtoResultWithPageInfo<dtoClazz>�����list+��ҳ��Ϣ��
	 * 
	 * @param page
	 * @param pageSize
	 * @param qo
	 * @param dtoClazz
	 * @return
	 */
/*	public <Q extends QueryObject, D extends Dto> DtoResultWithPageInfo<D> queryPageListByQueryObject(
			int page, int pageSize,Q qo, Class<D> dtoClazz){
		Criteria criteria = QueryObjectHelper.buildCriteria(qo, getSession());
		return queryPageListByCriteria(criteria, page, pageSize, dtoClazz);
	}*/
	
	/**
	 * ����QO ����List<dtoClazz>
	 * @param qo
	 * @param dtoClazz
	 * @return
	 */
	/*public <Q extends QueryObject,E, D extends Dto> List<D> queryListByQueryObject(
			Q qo, Class<D> dtoClazz){
		Criteria criteria = QueryObjectHelper.buildCriteria(qo, getSession());	
		@SuppressWarnings("unchecked")
		List<E> list = criteria.list();
		List<D> resultsDtoList = new ArrayList<D>();
		for(E entity:list){
			try {
				D dto = dtoClazz.newInstance();
				BeanUtils.copyProperties(entity, dto);
				resultsDtoList.add(dto);
			} catch (InstantiationException e) {
				log.error("dtoʵ���쳣��ExMsg==>"+e.getMessage());
			} catch (IllegalAccessException e) {
				log.error("dtoʵ���쳣��ExMsg==>"+e.getMessage());
			}
		}
		return resultsDtoList;
	}*/

	/**
	 * queryPageListByCriteria
	 * 
	 * ͨ��criteria ���� DtoResultWithPageInfo<dtoClazz>�����list+��ҳ��Ϣ��
	 * 
	 * @param criteria
	 *            ��ѯ����
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ����
	 * @param dtoClass
	 *            ��ݴ��ݶ���class
	 * 
	 */
	/*public <E, D extends Dto> DtoResultWithPageInfo<D> queryPageListByCriteria(
			Criteria criteria, int pageNo, int pageSize, Class<D> dtoClazz) {

		PageInfo pageInfo = getInstancePageInfoWithCriteria(criteria, pageNo,
				pageSize);
		criteria.setProjection(null);// ���ͶӰ
		criteria.setFirstResult(pageInfo.getFirstResultNum());
		criteria.setMaxResults(pageInfo.getPageSize());
		@SuppressWarnings("unchecked")
		List<E> resultsList = criteria.list();
		List<D> resultsDtoList = new ArrayList<D>();
		for (E result : resultsList) {
			D dto;
			try {
				dto = dtoClazz.newInstance();
				try {
					BeanUtils.copyProperties(result, dto);
				} catch (Exception e) {
					log.error("��ҳ��ѯ�쳣��bean�����쳣");
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			} catch (IllegalAccessException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			}
			resultsDtoList.add(dto);
		}
		DtoResultWithPageInfo<D> resultWithPageInfo = new DtoResultWithPageInfo<D>(
				resultsDtoList, pageInfo);
		return resultWithPageInfo;
	}*/
	
	/**
	 * ͨ��criteria ���� List<dtoClazz>
	 * 
	 * @param criteria
	 * @param dtoClazz
	 * @return
	 */
	/*public <E, D extends Dto> List<D> queryListByCriteria(
			Criteria criteria,Class<D> dtoClazz) {

		@SuppressWarnings("unchecked")
		List<E> resultsList = criteria.list();
		List<D> resultsDtoList = new ArrayList<D>();
		for (E result : resultsList) {
			D dto;
			try {
				dto = dtoClazz.newInstance();
				try {
					BeanUtils.copyProperties(result, dto);
				} catch (Exception e) {
					log.error("��ҳ��ѯ�쳣��bean�����쳣");
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			} catch (IllegalAccessException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			}
			resultsDtoList.add(dto);
		}
		return resultsDtoList;
	}*/
	
	/*public DataTablePageList queryDataTablePageListByCriteria(
			Criteria criteria, String displayStart, String displayLength) {
		// �����ܼ�¼��
		long totalRecords = 0L;
		criteria.setProjection(Projections.rowCount());
		totalRecords = (Long) criteria.uniqueResult();

		// ������
		criteria.setProjection(null);
		criteria.setFirstResult(Integer.parseInt(displayStart));
		criteria.setMaxResults(Integer.parseInt(displayLength));
		
		@SuppressWarnings("rawtypes")
		List resultsList = criteria.list();

		DataTablePageList dtpl = new DataTablePageList(
				String.valueOf((int) totalRecords), resultsList);
		return dtpl;
	}
	*/
	

	/**
	 * ͨ���ѯ������ʼ����ҳ������Ϣ
	 * 
	 * @param criteria
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
	private PageInfo getInstancePageInfoWithCriteria(Criteria criteria,
			int pageNo, int pageSize) {
		long totalQuantity = 0L;
		criteria.setProjection(Projections.rowCount());
		totalQuantity = (Long) criteria.uniqueResult();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageSize,
				totalQuantity);
		return pageInfo;
	}*/
	
	@Override
	public Criteria createCriteria() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(getEntityClass());
	}

	
	/**
	 * queryPageListByCriteria
	 * 
	 * ͨ��criteria ���� DtoResultWithPageInfo<dtoClazz>�����list+��ҳ��Ϣ��
	 * 
	 * @param criteria
	 *            ��ѯ����
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ����
	 * @param dtoClass
	 *            ��ݴ��ݶ���class
	 * ���ص������� DtoResultWithPageInfo 
	 * 
	 * ������Ϊ queryPageListByCriteria
	 */
	@Override
	public <E, D extends Dto> DtoResultWithPageInfo<D> queryPageListByCriteria(
			Criteria criteria, int pageNo, int pageSize, Class<D> dtoClazz) {
      //����˷����ĵ��ã�pageinfo���Ѿ�����firstResult �� maxresult
		PageInfo pageInfo = getInstancePageInfoWithCriteria(criteria, pageNo,
				pageSize);
		
		criteria.setProjection(null);// ���ͶӰ
		criteria.setFirstResult(pageInfo.getFirstResultNum());
		criteria.setMaxResults(pageInfo.getPageSize());
		@SuppressWarnings("unchecked")
		List<E> resultsList = criteria.list();
		List<D> resultsDtoList = new ArrayList<D>();
		for (E result : resultsList) {
			D dto;
			try {
				dto = dtoClazz.newInstance();
				try {
					BeanUtils.copyProperties(result, dto);
				} catch (Exception e) {
					log.error("��ҳ��ѯ�쳣��bean�����쳣");
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			} catch (IllegalAccessException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			}
			resultsDtoList.add(dto);
		}
		DtoResultWithPageInfo<D> resultWithPageInfo = new DtoResultWithPageInfo<D>(
				resultsDtoList, pageInfo);
		return resultWithPageInfo;
	}
	
	/**
	 * queryPageListByCriteriaWithQo
	 * 
	 * ͨ��criteria ���� DtoResultWithPageInfo<dtoClazz>�����list+��ҳ��Ϣ��
	 * 
	 * @param criteria
	 *            ��ѯ����
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ����
	 * @param dtoClass
	 *            ��ݴ��ݶ���class
	 * ���ص������� DtoResultWithPageInfo 
	 * 
	 * ������Ϊ queryPageListByCriteria
	 */
	@Override
	public <E, D extends Dto> DtoResultWithPageInfo<D> queryPageListByCriteriaWithQo(PageQo qo, Class<D> dtoClazz) {
      //����˷����ĵ��ã�pageinfo���Ѿ�����firstResult �� maxresult
		Criteria criteria = this.createCriteria();
		qo.add(criteria);
		PageInfo pageInfo = getInstancePageInfoWithCriteria(criteria, qo.getPage(),qo.getRows());
		
		criteria.setProjection(null);// ���ͶӰ
		criteria.setFirstResult(pageInfo.getFirstResultNum());
		criteria.setMaxResults(pageInfo.getPageSize());
		@SuppressWarnings("unchecked")
		List<E> resultsList = criteria.list();
		List<D> resultsDtoList = new ArrayList<D>();
		for (E result : resultsList) {
			D dto;
			try {
				dto = dtoClazz.newInstance();
				try {
					BeanUtils.copyProperties(result, dto);
				} catch (Exception e) {
					log.error("��ҳ��ѯ�쳣��bean�����쳣");
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			} catch (IllegalAccessException e) {
				log.error("��ҳ��ѯ�쳣��dto��ʼ���쳣");
				e.printStackTrace();
				dto = null;
			}
			resultsDtoList.add(dto);
		}
		DtoResultWithPageInfo<D> resultWithPageInfo = new DtoResultWithPageInfo<D>(
				resultsDtoList, pageInfo);
		return resultWithPageInfo;
	}
	
	
	
	/**
	 * ͨ���ѯ������ʼ����ҳ������Ϣ
	 * 
	 * @param criteria
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	private PageInfo getInstancePageInfoWithCriteria(Criteria criteria,
		int pageNo, int pageSize) {
			long totalQuantity = 0L;
         //		����ܵ�totalQuality
		criteria.setProjection(Projections.rowCount());
		totalQuantity = (Long) criteria.uniqueResult();
		
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageSize,
				totalQuantity);
		return pageInfo;
	}
}
