package com.lisbonbigapps.myhoster.database.resources;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.softmed.daos.GenericDAO;

import com.lisbonbigapps.myhoster.database.entities.EntityUser;
import com.lisbonbigapps.myhoster.database.util.DBAccess;

@SuppressWarnings("unchecked")
public class UserResourceFactory {
    public static String getCount() throws Exception {
	GenericDAO dao = new GenericDAO();
	dao.connect();

	Long count = dao.count(EntityUser.class);

	dao.rollback();
	return String.valueOf(count);
    }

    public static List<EntityUser> getList() throws Exception {
	List<EntityUser> todos;

	GenericDAO dao = new GenericDAO();
	dao.connect();

	todos = dao.get(EntityUser.class);

	dao.rollback();

	return todos;
    }

    public static EntityUser getUser(String username, String password) throws Exception {
	String query = "from " + EntityUser.class.getSimpleName() + " as user where user.username = '" + username + "' and user.password = '" + password + "'";

	EntityUser user = DBAccess.getElement(EntityUser.class, query);
	return user;
    }

    public static EntityUser getUser(String username) throws Exception {
	String query = "from " + EntityUser.class.getSimpleName() + " as user where user.username = '" + username + "'";

	EntityUser user = DBAccess.getElement(EntityUser.class, query);
	return user;
    }

    public static EntityUser getById(Long id) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	EntityUser bean = (EntityUser) dao.get(EntityUser.class, id);
	dao.rollback();

	return bean;
    }

    public static void create(EntityUser user) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	dao.store(user);

	dao.close();
    }

    public static void update(Long id, EntityUser user) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	EntityUser current = (EntityUser) dao.getCurrentVersion(user);
	BeanUtils.copyProperties(current, user);
	dao.update(current);

	dao.close();
    }

    public static void delete(Long id) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	EntityUser user = (EntityUser) dao.get(EntityUser.class, id);
	dao.delete(user);

	dao.close();
    }

    public static void update(EntityUser user) throws Exception {
	update(user.getId(), user);
    }
}