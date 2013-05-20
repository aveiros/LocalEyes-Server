package com.lisbonbigapps.myhoster.database.resources;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.softmed.daos.GenericDAO;

import com.lisbonbigapps.myhoster.database.entities.MHUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

@SuppressWarnings("unchecked")
public class UserResource {
	public static String getCount() throws Exception {
		GenericDAO dao = new GenericDAO();
		dao.connect();

		Long count = dao.count(MHUser.class);

		dao.rollback();
		return String.valueOf(count);
	}

	public static List<MHUser> getList() throws Exception {
		List<MHUser> todos;

		GenericDAO dao = new GenericDAO();
		dao.connect();

		todos = dao.get(MHUser.class);

		dao.rollback();

		return todos;
	}

	public static MHUser getUser(String username, String password) throws Exception {
		String query = "from " + MHUser.class.getSimpleName()
				+ " as user where user.username = '" + username
				+ "' and user.password = '" + password + "'";

		MHUser user = DBAccess.getElement(MHUser.class, query);
		return user;
	}
	
	public static MHUser getUser(String username) throws Exception {
		String query = "from " + MHUser.class.getSimpleName()
				+ " as user where user.username = '" + username
				+ "'";

		MHUser user = DBAccess.getElement(MHUser.class, query);
		return user;
	}

	public static MHUser getById(Long id) throws Exception {
		GenericDAO dao = new GenericDAO();

		dao.connect();
		MHUser bean = (MHUser) dao.get(MHUser.class, id);
		dao.rollback();

		return bean;
	}

	public static void create(MHUser user) throws Exception {
		GenericDAO dao = new GenericDAO();

		dao.connect();
		dao.store(user);
		
		dao.close();
	}

	public static void update(Long id, MHUser user) throws Exception {
		GenericDAO dao = new GenericDAO();
		
		dao.connect();
		MHUser current = (MHUser) dao.getCurrentVersion(user);
		BeanUtils.copyProperties(current, user);
		dao.update(current);

		dao.close();
	}

	public static void delete(Long id) throws Exception {
		GenericDAO dao = new GenericDAO();
		
		dao.connect();
		MHUser user = (MHUser) dao.get(MHUser.class, id);
		dao.delete(user);
		
		dao.close();
	}

	public static void update(MHUser user) throws Exception {
		update(user.getId(), user);
	}
}
