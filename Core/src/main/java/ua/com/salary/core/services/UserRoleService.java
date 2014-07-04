package ua.com.salary.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.salary.core.common.services.IUserRoleService;
import ua.com.salary.db.common.dao.IUserRoleDao;
import ua.com.salary.db.entity.User;
import ua.com.salary.db.entity.UserRole;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Service
public class UserRoleService implements IUserRoleService {

    @Autowired
    private Md5PasswordEncoder mMd5PasswordEncoder;

    @Autowired
    private IUserRoleDao mUserRoleDao;

    @Override
    @Transactional
    public void saveUserRole(UserRole userRole) {
        User user = userRole.getUser();
        String passwordEncoded = this.mMd5PasswordEncoder.encodePassword(user.getPassword(), user.getUsername());
        user.setPassword(passwordEncoded);
        this.mUserRoleDao.saveUserRole(userRole);
    }

}
