package ua.com.salary.web.validators;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.com.salary.common.regexp.ValidatePattern;
import ua.com.salary.common.service.IValidateService;
import ua.com.salary.db.entity.User;

import java.util.Date;
import java.util.Locale;

/**
 * @author Victor Zagnitko on 08.07.2014.
 */
@Service
public class UserValidatorService implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(UserValidatorService.class);

    @Autowired
    private MessageSource mMessageSource;

    @Autowired
    private IValidateService mValidateService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOG.info("Start regexp User");
        User user = (User) target;
        Preconditions.checkNotNull(user, "User must not null");
        Locale locale = LocaleContextHolder.getLocale();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null,
                this.mMessageSource.getMessage("reg.valid.email.empty", null, locale));
        if (!this.mValidateService.validate(ValidatePattern.EMAIL_VALIDATE_REG_EXP, user.getUsername())) {
            errors.rejectValue("username", null, null,
                    this.mMessageSource.getMessage("reg.valid.email.corr", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", null,
                this.mMessageSource.getMessage("reg.valid.first.empty", null, locale));
        if (user.getFirstName() != null && user.getFirstName().length() < 2) {
            errors.rejectValue("firstName", null, null,
                    this.mMessageSource.getMessage("reg.valid.first.size", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null,
                this.mMessageSource.getMessage("reg.valid.last.empty", null, locale));
        if (user.getFirstName() != null && user.getFirstName().length() < 2) {
            errors.rejectValue("lastName", null, null,
                    this.mMessageSource.getMessage("reg.valid.last.size", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", null,
                this.mMessageSource.getMessage("reg.valid.comp.empty", null, locale));
        if (user.getCompanyName() != null && user.getCompanyName().length() < 3) {
            errors.rejectValue("companyName", null, null,
                    this.mMessageSource.getMessage("reg.valid.comp.size", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", null,
                this.mMessageSource.getMessage("reg.valid.phone.empty", null, locale));
        if (!this.mValidateService.validate(ValidatePattern.PHONE_NUMBER_VALIDATE_REG_EXP, user.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", null, null,
                    this.mMessageSource.getMessage("reg.valid.phone.corr", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null,
                this.mMessageSource.getMessage("reg.valid.pass.empty", null, locale));
        if (!this.mValidateService.validate(ValidatePattern.PASSWORD_VALIDATE_REG_EXP, user.getPassword())) {
            errors.rejectValue("password", null, null,
                    this.mMessageSource.getMessage("reg.valid.pass.corr", null, locale));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", null,
                this.mMessageSource.getMessage("reg.valid.pass.conf.empty", null, locale));
        if (!this.mValidateService.validate(ValidatePattern.PASSWORD_VALIDATE_REG_EXP, user.getPassword())) {
            errors.rejectValue("confirmPassword", null, null,
                    this.mMessageSource.getMessage("reg.valid.pass.conf.corr", null, locale));
        }
        if (user.getPassword() != null && user.getConfirmPassword() != null &&
                user.getPassword().intern() != user.getConfirmPassword().intern()) {
            errors.rejectValue("confirmPassword", null, null,
                    this.mMessageSource.getMessage("reg.valid.pass.not.eq", null, locale));
        }
        user.setRegistrationDate(new Date());
    }

}
