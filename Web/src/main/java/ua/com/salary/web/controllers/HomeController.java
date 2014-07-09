package ua.com.salary.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView homeController() {
        return new ModelAndView("home-page");
    }

}
