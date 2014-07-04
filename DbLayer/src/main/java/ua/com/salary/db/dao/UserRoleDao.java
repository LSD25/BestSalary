package ua.com.salary.db.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ua.com.salary.db.common.dao.IUserRoleDao;
import ua.com.salary.db.entity.UserRole;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Repository
public class UserRoleDao extends ABasicDao implements IUserRoleDao {

    @Override
    public void saveUserRole(UserRole userRole) {
        Session session = this.mSessionFactory.getCurrentSession();
        session.save(userRole);
    }

}
