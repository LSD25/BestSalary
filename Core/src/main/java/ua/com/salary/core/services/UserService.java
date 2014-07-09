package ua.com.salary.core.services;

import com.google.common.base.Preconditions;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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

    @Autowired(required = false)
    private Md5PasswordEncoder mMd5PasswordEncoder;

    @Override
    @Transactional
    public User findUserById(long id) throws UsernameNotFoundException {
        return this.mUserDao.findUserById(id);
    }

    @Override
    @Transactional
    public User findUserByName(String name) throws UsernameNotFoundException {
        return this.mUserDao.findUserByName(name);
    }

    @Override
    @Transactional
    @TriggersRemove(cacheName = "dbCache", when = When.AFTER_METHOD_INVOCATION)
    public void saveUser(User user) {
        String passwordEncoded = this.mMd5PasswordEncoder.encodePassword(user.getPassword(), user.getUsername());
        user.setPassword(passwordEncoded);
        this.mUserDao.saveUser(user);
    }

    @Override
    @Transactional
    @Cacheable(cacheName = "dbCache")
    public boolean isExistUser(String username) throws UsernameNotFoundException {
        Preconditions.checkNotNull(username, "Username for check must not null");
        Preconditions.checkArgument(!username.isEmpty(), "Username for check is empty");
        findUserByName(username);
        return true;
    }

}
