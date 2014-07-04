package ua.com.salary.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        return new ModelAndView("welcome-page");
    }

}
