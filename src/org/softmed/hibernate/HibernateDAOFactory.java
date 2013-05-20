package org.softmed.hibernate;
 
import org.softmed.daos.IDAOFactory;
import org.softmed.daos.IGenericDAO;
 
public class HibernateDAOFactory implements IDAOFactory {
 
    @Override
    public IGenericDAO getDao() {
        return new HibernateGenericDAO();
    }
 
}
