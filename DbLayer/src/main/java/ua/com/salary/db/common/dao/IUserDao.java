package ua.com.salary.db.common.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.salary.db.entity.User;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
public interface IUserDao {

    User findUserById(long id) throws UsernameNotFoundException;

    User findUserByName(String name) throws UsernameNotFoundException;

    void saveUser(User user);

}
