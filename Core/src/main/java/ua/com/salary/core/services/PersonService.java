package ua.com.salary.core.services;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.salary.core.common.services.IPersonService;
import ua.com.salary.db.common.dao.IPersonDao;
import ua.com.salary.db.entity.Person;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 18.06.2014.
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonDao mPersonDao;

    public PersonService() {
        super();
    }

    @Override
    @Transactional
    @TriggersRemove(cacheName = "dbCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void savePerson(Person person) {
        this.mPersonDao.savePerson(person);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public Person getPersonById(int id) throws ObjectNotFoundException {
        return this.mPersonDao.getPersonById(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public List<Person> getPersons() {
        return this.mPersonDao.getPersons();
    }

}
