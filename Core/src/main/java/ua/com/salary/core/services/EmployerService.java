package ua.com.salary.core.services;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.salary.core.common.services.IEmployerService;
import ua.com.salary.db.common.dao.IEmployerDao;
import ua.com.salary.db.entity.Employer;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@Service
public class EmployerService implements IEmployerService {

    @Autowired
    private IEmployerDao mEmployerDao;

    public EmployerService() {
        super();
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public Employer getEmployerById(long id) throws ObjectNotFoundException {
        return this.mEmployerDao.getEmployerById(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public List<Employer> getEmployers() {
        return this.mEmployerDao.getEmployers();
    }

    @Override
    @Transactional
    @TriggersRemove(cacheName = "dbCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void addEmployer(Employer employer) {
        this.mEmployerDao.addEmployer(employer);
    }

}
