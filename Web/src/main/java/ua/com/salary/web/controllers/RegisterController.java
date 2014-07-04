package ua.com.salary.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.salary.core.common.services.IUserRoleService;
import ua.com.salary.db.entity.User;
import ua.com.salary.db.entity.UserRole;
import ua.com.salary.web.common.security.Roles;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Controller
@RequestMapping(value = "/register/user")
public class RegisterController {

    @Autowired
    private IUserRoleService mUserRoleService;

    @RequestMapping(value = {"", "/", "*"}, method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register-page");
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            User user = new User(username, password, false, true, true, true);
            UserRole userRole = new UserRole(Roles.ROLE_USER.name(), user);
            Set<UserRole> roles = new HashSet<>();
            roles.add(userRole);
            this.mUserRoleService.saveUserRole(userRole);
            return true;
        } catch (Exception exc) {
            exc.getStackTrace();
        }
        return false;
    }

}
