package ua.com.salary.web.controllers;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.salary.core.common.services.IUserRoleService;
import ua.com.salary.db.entity.Shop;
import ua.com.salary.db.entity.User;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Controller
@RequestMapping(value = "/register/user")
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserRoleService mUserRoleService;

    @RequestMapping(value = {"", "/", "*"}, method = RequestMethod.GET)
    public ModelAndView registerPage(Model model) {
        LOG.info("Start register user");
        ModelAndView modelAndView = new ModelAndView("register-page");
        Set<String> shops = new TreeSet<>();
        Arrays.asList(Shop.values()).parallelStream().forEach(shop -> shops.add(StringUtils.capitalize(shop.name().toLowerCase())));
        LOG.info("Finded shops:" + shops);
        modelAndView.addObject("shops", shops);
        model.addAttribute("user", new User());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean registerUser(@Valid User user, BindingResult result) {
        try {
            LOG.info("Start registration user: " + user.getUsername());
            if(result.hasErrors()) {
                LOG.error("Error validation user: " + user.getUsername());
            }
//            User user = new User(username, password, false, true, true, true, "first", "last", "company", "phone",);
//            UserRole userRole = new UserRole(Roles.ROLE_USER.name(), user);
//            Set<UserRole> roles = new HashSet<>();
//            roles.add(userRole);
//            this.mUserRoleService.saveUserRole(userRole);
//            return true;
        } catch (Exception exc) {
            exc.getStackTrace();
        }
        return false;
    }

}
