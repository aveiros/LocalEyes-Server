package com.lisbonbigapps.myhoster.database.resources;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.softmed.daos.GenericDAO;
import org.softmed.services.PersistentObject;

@SuppressWarnings("unchecked")
public class GenericResourceFactory<T extends PersistentObject> {
    private Class<T> type;

    public Class<T> getType() {
	return type;
    }

    public String getCount() throws Exception {
	GenericDAO dao = new GenericDAO();
	dao.connect();

	Long count = dao.count(this.getType());

	dao.rollback();
	return String.valueOf(count);
    }

    public List<T> getList() throws Exception {
	List<T> todos;

	GenericDAO dao = new GenericDAO();
	dao.connect();

	todos = dao.get(this.getType());

	dao.rollback();

	return todos;
    }

    public T getById(Long id) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	T bean = (T) dao.get(this.getType(), id);
	dao.rollback();

	return bean;
    }

    public void create(T entity) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();

	dao.store(entity);
	dao.close();
    }

    public void update(Long id, T entity) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();

	T current = (T) dao.getCurrentVersion(entity);
	BeanUtils.copyProperties(current, entity);
	dao.update(current);

	dao.close();
    }

    public void delete(Long id) throws Exception {
	GenericDAO dao = new GenericDAO();

	dao.connect();
	T entity = (T) dao.get(this.getType(), id);
	dao.delete(entity);

	dao.close();
    }

    public void update(T entity) throws Exception {
	update(entity.getId(), entity);
    }
}
