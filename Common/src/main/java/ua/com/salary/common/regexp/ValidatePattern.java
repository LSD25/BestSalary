package ua.com.salary.common.regexp;

/**
 * @author Victor Zagnitko on 07.07.2014.
 */
public final class ValidatePattern {

    public static final String PHONE_NUMBER_VALIDATE_REG_EXP = "\\(\\d{3}\\)-\\d{3}-\\d{4}"; //(123)-456-7890

    public static final String PASSWORD_VALIDATE_REG_EXP = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"; //mkyong1A@

    public static final String EMAIL_VALIDATE_REG_EXP = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

    public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

}
