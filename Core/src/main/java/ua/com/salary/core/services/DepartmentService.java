package ua.com.salary.core.services;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.salary.core.common.services.IDepartmentService;
import ua.com.salary.db.common.dao.IDepartmentDao;
import ua.com.salary.db.entity.Department;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.List;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentDao mDepartmentDao;

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public Department getDepartmentById(long id) throws ObjectNotFoundException {
        return this.mDepartmentDao.getDepartmentById(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public List<Department> getDepartments() {
        return this.mDepartmentDao.getDepartments();
    }

    @Override
    @Transactional
    @TriggersRemove(cacheName = "dbCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
    public void addDepartment(Department department) {
        this.mDepartmentDao.addDepartment(department);
    }

}
