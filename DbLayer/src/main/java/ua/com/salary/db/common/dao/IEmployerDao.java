package ua.com.salary.db.common.dao;

import ua.com.salary.db.entity.Employer;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
public interface IEmployerDao {

    Employer getEmployerById(long id) throws ObjectNotFoundException;

    List<Employer> getEmployers();

    void addEmployer(Employer employer);

}
