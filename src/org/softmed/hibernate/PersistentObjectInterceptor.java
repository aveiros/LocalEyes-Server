package org.softmed.hibernate;
 
import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.softmed.services.PersistentObject;
 
public class PersistentObjectInterceptor extends EmptyInterceptor {
 
    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        // do nothing
    }
 
    public boolean onLoad(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        if (entity instanceof PersistentObject) {
            PersistentObject po = (PersistentObject) entity;
            po.loadMiliseconds();
            return true;
        }
        return false;
    }
 
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
 
        if (entity instanceof PersistentObject) {
            // PersistentObject po = (PersistentObject) entity;
            // po.saveMiliseconds();
 
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals("updated")) {
                    state[i] = new Date();
                } else if (propertyNames[i].equals("updatedMilis")) {
                    state[i] = new Date().getTime();
                }
            }
 
            return true;
        }
        return false;
    }
 
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
 
        if (entity instanceof PersistentObject) {
            // PersistentObject po = (PersistentObject) entity;
            // po.saveMiliseconds();
 
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals("updated")) {
                    currentState[i] = new Date();
                } else if (propertyNames[i].equals("updatedMilis")) {
                    currentState[i] = new Date().getTime();
                }
            }
 
            return true;
        }
 
        return false;
    }
 
}
