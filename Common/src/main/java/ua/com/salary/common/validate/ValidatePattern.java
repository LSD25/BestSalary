package ua.com.salary.common.validate;

/**
 * @author Victor Zagnitko on 07.07.2014.
 */
public final class ValidatePattern {

    public static final String PHONE_NUMBER_VALIDATE_REG_EXP = "\\(\\d{3}\\)-\\d{3}-\\d{4}";

    public static final String PASSWORD_VALIDATE_REG_EXP = "((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

}
