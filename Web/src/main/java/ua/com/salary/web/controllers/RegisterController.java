package ua.com.salary.web.controllers;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.salary.core.common.services.IUserRoleService;
import ua.com.salary.db.entity.Shop;
import ua.com.salary.db.entity.User;
import ua.com.salary.web.validators.UserValidatorService;

import javax.validation.Valid;
import java.util.*;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Controller
@RequestMapping(value = "/register/user")
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserRoleService mUserRoleService;

    @Autowired
    private UserValidatorService mUserValidatorService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(mUserValidatorService);
    }

    @RequestMapping(value = {"", "/", "*"}, method = RequestMethod.GET)
    public ModelAndView registerPage(@ModelAttribute User user) {
        LOG.info("Start register user");
        Set<String> shops = new TreeSet<>();
        Arrays.asList(Shop.values()).parallelStream().forEachOrdered(shop -> shops.add(StringUtils.capitalize(shop.name().toLowerCase())));
        LOG.info("Finded shops:" + shops);
        return new ModelAndView("register-page").addObject("shops", shops);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, ?> registerUser(@Valid User user, BindingResult result) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            LOG.info("Start registration user: " + user.getUsername());
            if (result.hasErrors()) {
                LOG.error("Error validation user: " + user.getUsername());
                result.getAllErrors().stream().map(objectError -> {
                    response.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
                    return objectError;
                }).forEachOrdered(o -> LOG.info("Error with field: " + o));
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
        return response;
    }

}
