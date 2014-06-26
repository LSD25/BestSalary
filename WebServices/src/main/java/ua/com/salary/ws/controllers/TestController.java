package ua.com.salary.ws.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.salary.core.common.services.IDepartmentService;
import ua.com.salary.core.common.services.IEmployerService;
import ua.com.salary.core.common.services.IPersonService;
import ua.com.salary.db.entity.Department;
import ua.com.salary.db.entity.Employer;
import ua.com.salary.db.entity.Person;
import ua.com.salary.db.exception.ObjectNotFoundException;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Виктор on 18.06.2014.
 */
@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IPersonService mPersonService;

    @Autowired
    private IDepartmentService mDepartmentService;

    @Autowired
    private IEmployerService mEmployerService;

    public TestController() {
        super();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, ?> test() {
        LOG.info("ddd");
        Map<String, Object> testMap = new Hashtable<>();
        testMap.put("test", "ddd");
        testMap.put("sfdsfs", Arrays.asList("ddff", "setwe"));
        return testMap;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPersonByIdController(@PathVariable int id) throws ObjectNotFoundException {
        return mPersonService.getPersonById(id);
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.GET)
    public boolean addPerson(@RequestParam String name, @RequestParam String age) {
        try {
            Person person = new Person();
            person.setName(name);
            person.setAge(Integer.valueOf(age));
            mPersonService.savePerson(person);
            return true;
        } catch (Exception exc) {
            exc.getStackTrace();
        }
        return false;
    }

    @RequestMapping(value = "/employer/save", method = RequestMethod.GET)
    public boolean addEmployer(@RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String phoneNumber, @RequestParam String depName) {
        try {
            Department department = new Department();
            department.setDepartmentName(depName);
            this.mDepartmentService.addDepartment(department);

            Employer employer = new Employer();
            employer.setFirstName(firstName);
            employer.setLastName(lastName);
            employer.setPhoneNumber(phoneNumber);
            employer.setDepartment(department);
            this.mEmployerService.addEmployer(employer);
        } catch (Exception exc) {
            exc.getStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/employers/save", method = RequestMethod.GET)
    public boolean addEmployer() {
        try {
            Department department = new Department();
            department.setDepartmentName("JS");
            this.mDepartmentService.addDepartment(department);

            Employer employer = new Employer();
            employer.setFirstName("first1");
            employer.setLastName("last1");
            employer.setPhoneNumber("phone1");
            employer.setDepartment(department);

            Employer employer2 = new Employer();
            employer2.setFirstName("first2");
            employer2.setLastName("last2");
            employer2.setPhoneNumber("phone2");
            employer2.setDepartment(department);

            this.mEmployerService.addEmployer(employer);
            this.mEmployerService.addEmployer(employer2);
        } catch (Exception exc) {
            exc.getStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department findDepartmentController(@PathVariable String id) throws ObjectNotFoundException {
        return this.mDepartmentService.getDepartmentById(Long.valueOf(id));
    }

    @RequestMapping(value = "/employe/{id}", method = RequestMethod.GET)
    public Employer findEmployerController(@PathVariable String id) throws ObjectNotFoundException {
        return this.mEmployerService.getEmployerById(Long.valueOf(id));
    }

    @RequestMapping(value = "/person/all", method = RequestMethod.GET)
    public List<Person> getPersonsController() {
        return this.mPersonService.getPersons();
    }

}
