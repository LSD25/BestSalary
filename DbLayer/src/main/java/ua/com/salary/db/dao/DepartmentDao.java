package ua.com.salary.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.com.salary.db.common.dao.IDepartmentDao;
import ua.com.salary.db.entity.Department;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@Repository
public class DepartmentDao extends ABasicDao implements IDepartmentDao {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);

    public DepartmentDao() {
        super();
    }

    @Override
    public Department getDepartmentById(long id) throws ObjectNotFoundException {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getDepartmentById");
        query.setLong("id", id);
        Department department = (Department) query.uniqueResult();
        if (department == null) {
            throw new ObjectNotFoundException("Department entity with id:" + id + " not found in database!!!");
        }
        return department;
    }

    @Override
    public List<Department> getDepartments() {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("getDepartments");
        return query.list();
    }

    @Override
    public void addDepartment(Department department) {
        Session session = this.mSessionFactory.getCurrentSession();
        session.save(department);
    }

}
