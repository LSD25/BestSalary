package ua.com.salary.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Виктор on 17.06.2014.
 */
@Controller
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    public TestController() {
        super();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView testController() {
        return new ModelAndView("home");
    }

}
