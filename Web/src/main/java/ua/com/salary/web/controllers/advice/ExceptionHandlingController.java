package ua.com.salary.web.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ModelAndView accessDenied() {
        return new ModelAndView("access-denied");
    }

}
