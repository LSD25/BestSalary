package ua.com.salary.db.common.dao;

import ua.com.salary.db.entity.Department;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
public interface IDepartmentDao {

    Department getDepartmentById(long id) throws ObjectNotFoundException;

    List getDepartments();

    void addDepartment(Department department);

}
