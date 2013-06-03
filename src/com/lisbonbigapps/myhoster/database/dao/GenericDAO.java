package com.lisbonbigapps.myhoster.database.dao;

import java.util.List;

import org.softmed.services.PersistentObject;

import com.lisbonbigapps.myhoster.database.util.DBAccess;

public class GenericDAO<T extends PersistentObject> {
    private final Class<T> type;

    public Class<T> getType() {
	return this.type;
    }

    public GenericDAO(Class<T> type) {
	this.type = type;
    }

    public long create(T entity) {
	return DBAccess.saveItem(entity);
    }

    public void update(T entity) {
	DBAccess.updateItem(entity);
    }

    public void delete(T entity) {
	DBAccess.deleteItem(entity);
    }

    public int getCount() throws Exception {
	String query = "from %s as entity";
	String fquery = String.format(query, this.getType().getSimpleName());
	return DBAccess.getDBItem(this.getType(), fquery).size();
    }

    public List<T> getList() throws Exception {
	String query = "from %s as entity";
	String fquery = String.format(query, this.getType().getSimpleName());
	return DBAccess.getDBItem(this.getType(), fquery);
    }

    public T findById(long id) {
	String query = "from %s as entity where entity.id = %d";
	String fquery = String.format(query, this.getType().getSimpleName(), id);
	return DBAccess.getElement(this.getType(), fquery);
    }
}