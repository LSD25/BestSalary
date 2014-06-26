package ua.com.salary.db.common.dao;

import ua.com.salary.db.entity.Person;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 19.06.2014.
 */
public interface IPersonDao {

    void savePerson(Person person);

    Person getPersonById(int id) throws ObjectNotFoundException;

    List<Person> getPersons();

}
