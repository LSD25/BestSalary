package ua.com.salary.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ua.com.salary.db.common.dao.IUserDao;
import ua.com.salary.db.entity.User;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Repository
public class UserDao extends ABasicDao implements IUserDao {

    @Override
    public User findUserById(long id) throws UsernameNotFoundException {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("findUserById");
        query.setLong("id", id);
        User user = (User) query.uniqueResult();
        if (user == null) {
            throw new UsernameNotFoundException("User with id:" + id + " not found in database");
        }
        return user;
    }

    @Override
    public User findUserByName(String name) throws UsernameNotFoundException {
        Session session = this.mSessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("findUserByName");
        query.setString("username", name);
        User user = (User) query.uniqueResult();
        if (user == null) {
            throw new UsernameNotFoundException("User with name:" + name + " not found in database");
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = this.mSessionFactory.getCurrentSession();
        session.save(user);
    }

}
