package org.softmed.hibernate;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.softmed.daos.IGenericDAO;
import org.softmed.daos.Parameter;
import org.softmed.daos.strategies.AllEntitiesStrategy;
import org.softmed.daos.strategies.SearchEqualStrategy;
import org.softmed.daos.strategies.SearchLikeStrategy;
import org.softmed.daos.strategies.SearchMultipleLikeStrategy;

public class HibernateGenericDAO implements IGenericDAO {

	Session session;
	Transaction transaction = null;

	// String customCountQuery;

	@Override
	public void pessimisticLock(Object obj) throws Exception {
		try {

			isConnected();

			session.lock(obj, LockMode.PESSIMISTIC_WRITE);

		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	@Override
	public Long store(Object obj) throws Exception {
		try {

			isConnected();
			Long index = null;

			index = (Long) session.save(obj);
			return index;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}

	}

	@Override
	public void connect() throws Exception {
		try {
			if (session != null)
				throw new Exception("Session is opened !!!");

			if (session != null && (session.isOpen() || session.isConnected()))
				throw new Exception("Session is opened !!!");

			if (transaction != null)
				throw new Exception("Transaction is active !!!");

			if (transaction != null && transaction.isActive())
				throw new Exception("Transaction is active !!!");

			session = HibernateUtil.getSessionFactory().openSession();
			// session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
		} catch (Exception t) {
			silentClosing();
			throw t;
		}
	}

	@Override
	public void close() throws Exception {
		try {
			transaction.commit();

			session.flush();
			session.clear();

			session.disconnect();
			session.close();

			transaction = null;
			session = null;
		} catch (Exception t) {
			silentClosing();
			throw t;
		}
	}

	private void silentClosing() {
		try {
			if (transaction != null)
				transaction.rollback();
		} catch (Exception t) {
			// silent handling;
			t.printStackTrace();
		} finally {
			try {
				// session.disconnect();
				if (session != null)
					session.close();

			} catch (Exception t) {
				// silent handling;
				t.printStackTrace();
			}
		}
		session = null;
		transaction = null;
	}

	public void rollback() {
		silentClosing();
	}

	public void update(Object obj) throws Exception {
		try {
			isConnected();

			// session.evict(obj);
			// session.flush();
			// session.clear();
			session.update(obj);
			// session.merge(obj); //fixed the NonUnique object exception....
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	public List get(Class type) throws Exception {
		try {
			isConnected();

			List list = session.createQuery("from " + type.getSimpleName())
					.setCacheable(true).list();
			// List list = session.createQuery("from " + type.getSimpleName())
			// .setCacheable(true).list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	public List queryLike(Class type, String field, String value)
			throws Exception {
		try {
			isConnected();

			SearchLikeStrategy strategy = new SearchLikeStrategy(type, field,
					value);

			List list = session.createQuery(strategy.getQuery()).list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}

	}

	public List queryEqual(Class type, String field, String value)
			throws Exception {
		try {
			isConnected();

			AllEntitiesStrategy root = new AllEntitiesStrategy(type);

			SearchEqualStrategy strategy = new SearchEqualStrategy(field, value);

			root.setQueryBuilder(strategy);

			List list = session.createQuery(root.getQuery()).list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}

	}

	public List queryMultipleLike(Class type, String value, String fieldNames)
			throws Exception {
		try {
			isConnected();

			AllEntitiesStrategy root = new AllEntitiesStrategy(type);

			SearchMultipleLikeStrategy strategy = new SearchMultipleLikeStrategy(
					value, fieldNames);

			root.setQueryBuilder(strategy);

			List list = session.createQuery(root.getQuery()).list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	@Override
	public Object get(Class type, Long id) throws Exception {
		try {
			isConnected();

			return session.get(type, id);
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	@Override
	public Object getPessimistic(Class type, Long id) throws Exception {
		try {
			isConnected();

			return session.get(type, id, LockMode.PESSIMISTIC_WRITE);
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	@Override
	public List getByObject(Object obj) throws Exception {
		return get(obj.getClass());
	}

	@Override
	public List query(String query) throws Exception {
		try {
			isConnected();

			List list = session.createQuery(query).list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	public void delete(Object obj) throws Exception {
		try {
			isConnected();

			session.delete(obj);
		} catch (HibernateException e) {
			// e.printStackTrace();
			silentClosing();
			throw e;
		}
	}

	protected void isConnected() throws Exception {
		if (session == null || transaction == null || !session.isOpen()
				|| !session.isConnected() || !transaction.isActive())
			throw new Exception("No active session or transaction!");
	}

	@Override
	public List queryInterval(String queryString, int firstIndex, int maxResults)
			throws Exception {
		try {
			isConnected();
			Query query;

			query = session.createQuery(queryString);
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxResults);
			List list = query.list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}

	}

	@Override
	public List queryPage(String query, int page, int pageSize)
			throws Exception {
		int firstIndex = (page - 1) * pageSize;
		return queryInterval(query, firstIndex, pageSize);
	}

	@Override
	public List getInterval(Class type, int firstIndex, int maxResults)
			throws Exception {

		return queryInterval("from " + type.getSimpleName(), firstIndex,
				maxResults);

	}

	@Override
	public List getPage(Class type, int page, int pageSize) throws Exception {
		int firstIndex = (page - 1) * pageSize;
		return getInterval(type, firstIndex, pageSize);
	}

	@Override
	public Long getObjectId(Object obj) throws Exception {
		Long id = (Long) obj.getClass().getMethod("getId", null)
				.invoke(obj, null);

		return id;
	}

	@Override
	public Date getObjectVersion(Object obj) throws Exception {
		Method m = obj.getClass().getMethod("getUpdated", null);
		return (Date) m.invoke(obj, null);
	}

	@Override
	public PROBLEM isThereConcurrencyProblems(Object obj) throws Exception {
		try {
			isConnected();

			// TODO check if object exists
			Date oldVersion = getObjectVersion(obj);
			Object current = null;

			Long id = getObjectId(obj);

			current = get(obj.getClass(), id);

			if (current == null)
				return PROBLEM.DELETED;

			Date currentVersion = getObjectVersion(current);

			if (!currentVersion.equals(oldVersion))
				return PROBLEM.CHANGED;

			return PROBLEM.NONE;

		} catch (Exception t) {
			silentClosing();
			throw t;
		}
	}

	@Override
	public boolean doesObjectExist(Object obj) throws Exception {

		try {

			isConnected();
			Long id = getObjectId(obj);

			Object obj2 = get(obj.getClass(), id);
			return obj != null;
		} catch (Exception t) {
			silentClosing();
			throw t;
		}

	}

	@Override
	public Object getCurrentVersion(Object obj) throws Exception {

		try {
			isConnected();

			Long id = getObjectId(obj);
			Object current = get(obj.getClass(), id);
			return current;
		} catch (Exception t) {
			silentClosing();
			throw t;
		}

	}

	@Override
	public Long getCount(String query) throws Exception {

		try {
			isConnected();
			// if (customCountQuery == null) {
			// String cquery = "select count(*) " + query;
			// List list = session.createQuery(cquery).list();
			// Long size = ((Long) list.iterator().next());
			// return size;
			// } else {
			List list = session.createQuery(query).list();
			Long size = ((Long) list.iterator().next());
			return size;
			// }

		} catch (Exception t) {
			silentClosing();
			throw t;
		}

	}

	@Override
	public Long count(Class type) throws Exception {
		return getCount("select count(*) from " + type.getSimpleName());
	}

	@Override
	public void merge(Object obj) throws Exception {
		try {
			isConnected();

			session.merge(obj);

		} catch (HibernateException e) {
			silentClosing();
			throw e;

		}

	}

	@Override
	public List queryWithParameters(String queryText, Parameter... parameters)
			throws Exception {
		try {
			isConnected();

			Query query = session.createQuery(queryText);

			for (Parameter p : parameters) {
				query.setParameter(p.getName(), p.getValue());
			}

			List list = query.list();
			return list;
		} catch (HibernateException e) {
			silentClosing();
			throw e;
		}
	}

	// public String getCustomCountQuery() {
	// return customCountQuery;
	// }
	//
	// public void setCustomCountQuery(String customCountQuery) {
	// this.customCountQuery = customCountQuery;
	// }

}
