package ua.com.salary.core.services;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.salary.core.common.services.IUserService;
import ua.com.salary.db.common.dao.IUserDao;
import ua.com.salary.db.entity.User;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao mUserDao;

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public User findUserById(long id) throws UsernameNotFoundException {
        return this.mUserDao.findUserById(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public User findUserByName(String name) throws UsernameNotFoundException {
        return this.mUserDao.findUserByName(name);
    }

    @Override
    @Transactional
    @TriggersRemove(cacheName = "dbCache", when = When.AFTER_METHOD_INVOCATION)
    public void saveUser(User user) {
        this.mUserDao.saveUser(user);
    }

}
