package ua.com.salary.ws.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Виктор on 18.06.2014.
 */
@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, ?> test() {
        LOG.info("ddd");
        Map<String, Object> testMap = new ConcurrentHashMap<>();
        testMap.put("test", "ddd");
        testMap.put("sfdsfs", Arrays.asList("ddff", "setwe"));
        return testMap;
    }

}
