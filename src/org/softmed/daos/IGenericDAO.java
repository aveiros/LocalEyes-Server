package org.softmed.daos;

import java.util.Date;
import java.util.List;

public interface IGenericDAO {

	public static enum PROBLEM {
		NONE, DELETED, CHANGED
	};

	public void connect() throws Exception;

	public void close() throws Exception;

	public Long store(Object obj) throws Exception;

	public void merge(Object obj) throws Exception;

	public void update(Object obj) throws Exception;

	public void delete(Object obj) throws Exception;

	public List get(Class type) throws Exception;

	public void pessimisticLock(Object obj) throws Exception;

	public List getInterval(Class type, int firstIndex, int maxResults)
			throws Exception;

	public List getPage(Class type, int page, int pageSize) throws Exception;

	public List getByObject(Object obj) throws Exception;

	public Object get(Class type, Long id) throws Exception;

	public Object getPessimistic(Class type, Long id) throws Exception;

	public List queryWithParameters(String query, Parameter... parameters)
			throws Exception;

	public List query(String query) throws Exception;

	public List queryLike(Class type, String field, String value)
			throws Exception;

	public List queryEqual(Class type, String field, String value)
			throws Exception;

	public List queryMultipleLike(Class type, String value, String fieldNames)
			throws Exception;

	public Long getObjectId(Object obj) throws Exception;

	public Date getObjectVersion(Object obj) throws Exception;

	public PROBLEM isThereConcurrencyProblems(Object obj) throws Exception;

	public boolean doesObjectExist(Object obj) throws Exception;

	public Object getCurrentVersion(Object obj) throws Exception;

	List queryInterval(String queryString, int firstIndex, int maxResults)
			throws Exception;

	List queryPage(String query, int page, int pageSize) throws Exception;

	Long getCount(String query) throws Exception;

	Long count(Class type) throws Exception;

	// public String getCustomCountQuery();
	//
	// public void setCustomCountQuery(String customCountQuery);

	public void rollback();

}
