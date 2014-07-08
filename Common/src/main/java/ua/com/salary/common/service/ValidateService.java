package ua.com.salary.common.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Victor Zagnitko on 08.07.2014.
 */
@Service
public class ValidateService implements IValidateService {

    /**
     * Validate password with regular expression
     *
     * @param validate password for validation
     * @return true valid password, false invalid password
     */
    @Override
    public boolean validate(String regExp, String validate) {
        if (StringUtils.isEmpty(validate)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }

}
