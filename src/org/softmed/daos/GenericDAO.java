package org.softmed.daos;

import java.util.Date;
import java.util.List;

//import org.softmed.daos.logic.LogicProxyDAO;
//
//import com.google.inject.Inject;

public class GenericDAO implements IGenericDAO {

	static IDAOFactory daoFactory;
	IGenericDAO dao;

	public GenericDAO() {
		dao = daoFactory.getDao();
	}

	@Override
	public void close() throws Exception {
		dao.close();

	}

	@Override
	public void connect() throws Exception {
		dao.connect();

	}

	public Object getPessimistic(Class type, Long id) throws Exception {
		return dao.getPessimistic(type, id);
	}

	@Override
	public void delete(Object obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public List get(Class type) throws Exception {

		return dao.get(type);
	}

	@Override
	public Long store(Object obj) throws Exception {
		return dao.store(obj);
	}

	@Override
	public void update(Object obj) throws Exception {
		dao.update(obj);

	}

	@Override
	public List queryEqual(Class type, String field, String value)
			throws Exception {
		return dao.queryEqual(type, field, value);
	}

	@Override
	public List queryLike(Class type, String field, String value)
			throws Exception {
		return dao.queryLike(type, field, value);
	}

	public List queryMultipleLike(Class type, String value, String fieldNames)
			throws Exception {
		return dao.queryMultipleLike(type, value, fieldNames);
	}

	@Override
	public Object get(Class type, Long id) throws Exception {
		return dao.get(type, id);
	}

	@Override
	public List getByObject(Object obj) throws Exception {
		return dao.getByObject(obj);
	}

	@Override
	public List query(String query) throws Exception {

		return dao.query(query);
	}

	@Override
	public boolean doesObjectExist(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.doesObjectExist(obj);
	}

	@Override
	public Long getObjectId(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.getObjectId(obj);
	}

	@Override
	public Date getObjectVersion(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.getObjectVersion(obj);
	}

	@Override
	public PROBLEM isThereConcurrencyProblems(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.isThereConcurrencyProblems(obj);
	}

	@Override
	public Object getCurrentVersion(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.getCurrentVersion(obj);
	}

	@Override
	public List getInterval(Class type, int firstIndex, int maxResults)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getInterval(type, firstIndex, maxResults);
	}

	@Override
	public List getPage(Class type, int page, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPage(type, page, pageSize);
	}

	@Override
	public List queryInterval(String queryString, int firstIndex, int maxResults)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.queryInterval(queryString, firstIndex, maxResults);
	}

	@Override
	public List queryPage(String query, int page, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.queryPage(query, page, pageSize);
	}

	@Override
	public Long getCount(String query) throws Exception {
		// TODO Auto-generated method stub
		return dao.getCount(query);
	}

	@Override
	public Long count(Class type) throws Exception {
		// TODO Auto-generated method stub
		return dao.count(type);
	}

	@Override
	public void merge(Object obj) throws Exception {
		dao.merge(obj);

	}

	// @Override
	// public String getCustomCountQuery() {
	// return dao.getCustomCountQuery();
	// }
	//
	// @Override
	// public void setCustomCountQuery(String customCountQuery) {
	// dao.setCustomCountQuery(customCountQuery);
	//
	// }

	@Override
	public void rollback() {
		dao.rollback();

	}

	@Override
	public List queryWithParameters(String query, Parameter... parameters)
			throws Exception {

		return dao.queryWithParameters(query, parameters);
	}

	public static IDAOFactory getDaoFactory() {
		return daoFactory;
	}

	public static void setDaoFactory(IDAOFactory daoFactory) {
		GenericDAO.daoFactory = daoFactory;
	}

	@Override
	public void pessimisticLock(Object obj) throws Exception {
		dao.pessimisticLock(obj);

	}

}
