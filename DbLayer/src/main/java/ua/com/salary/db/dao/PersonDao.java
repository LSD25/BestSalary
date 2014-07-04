package ua.com.salary.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.salary.db.common.dao.IPersonDao;
import ua.com.salary.db.entity.Person;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 18.06.2014.
 */
@Repository
public class PersonDao extends ABasicDao implements IPersonDao {

    private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);

    @Override
    public void savePerson(Person person) {
        Session session = this.mSessionFactory.getCurrentSession();
        session.save(person);
    }

    @Override
    public Person getPersonById(int id) throws ObjectNotFoundException {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getPersonById");
        query.setLong("id", id);
        Person person = (Person) query.uniqueResult();
        if (person == null) {
            throw new ObjectNotFoundException("Person with id: " + id + " not found in database!!!");
        }
        return person;
    }

    @Override
    public List<Person> getPersons() {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getPersons");
        return query.list();
    }

}
