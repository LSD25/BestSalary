package ua.com.salary.ws.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.com.salary.db.exception.ObjectNotFoundException;

/**
 * @author Victor Zagnitko on 25.06.2014.
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    public ExceptionHandlingController() {
        super();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public String handleConflict() {
        return "We have a problem with found item";
    }

}
