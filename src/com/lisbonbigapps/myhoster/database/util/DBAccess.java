package com.lisbonbigapps.myhoster.database.util;

import java.util.ArrayList;
import java.util.List;

import org.softmed.daos.GenericDAO;

@SuppressWarnings({ "rawtypes" })
public class DBAccess {
	
	public static List getDBItem(Class type, String q0, String q1) {
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.queryEqual(type, q0, q1);
			dao.close();
			return qR;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public static long saveItem(Object obj) {
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			long id = dao.store(obj);
			dao.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void updateItem(Object obj){
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			dao.update(obj);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteItem(Object obj){
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			dao.delete(obj);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List getDBItem(Class type){
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.get(type);
			dao.close();
			return qR;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public static List getDBItem(Class type, String query){
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.query(query);
			dao.close();
			return qR;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public static <T> T getElement(Class<T> _class) {
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.get(_class);
			dao.close();
			if (qR.size() > 0){
				return _class.cast(qR.get(0));
			} else {
				return _class.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T getElement(Class<T> _class, String query) {
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.query(query);
			dao.close();
			if (qR.size() > 0){
				return _class.cast(qR.get(0));
			} else {
				return _class.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	public static <T> T getElement(Class<T> _class, String form, String value) {
		GenericDAO dao = new GenericDAO();
		try {
			dao.connect();
			List qR = dao.queryEqual(_class, form, value);
			dao.close();
			if (qR.size() > 0){
				return _class.cast(qR.get(0));
			} else {
				return _class.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database access error");
			return null;
		}
	} 
	
}
