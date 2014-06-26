package ua.com.salary.db.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Victor Zagnitko on 26.06.2014.
 */
public abstract class ABasicDao {

    @Autowired
    protected SessionFactory mSessionFactory;

    public ABasicDao() {
        super();
    }

}
