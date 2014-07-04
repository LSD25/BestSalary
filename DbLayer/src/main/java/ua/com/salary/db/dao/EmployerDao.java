package ua.com.salary.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.salary.db.common.dao.IEmployerDao;
import ua.com.salary.db.entity.Employer;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@Repository
public class EmployerDao extends ABasicDao implements IEmployerDao {

    private static final Logger LOG = LoggerFactory.getLogger(EmployerDao.class);

    @Override
    public Employer getEmployerById(long id) throws ObjectNotFoundException {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getEmployerById");
        query.setLong("id", id);
        Employer employer = (Employer) query.uniqueResult();
        if (employer == null) {
            throw new ObjectNotFoundException("Employer with id: " + id + " not found in database");
        }
        return employer;
    }

    @Override
    public List<Employer> getEmployers() {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getEmployers");
        return query.list();
    }

    @Override
    public void addEmployer(Employer employer) {
        Session session = this.mSessionFactory.getCurrentSession();
        session.save(employer);
    }

}