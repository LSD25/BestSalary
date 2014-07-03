package ua.com.salary.db.config.local;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ua.com.salary.db.config.ABasicConditional;
import ua.com.salary.db.config.Environment;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
public class LocalhostCondition extends ABasicConditional {

    public LocalhostCondition() {
        super();
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            return System.getenv().get(ENVIRONMENT).intern() == Environment.LOCALHOST.getEnv();
        } catch (Exception exc) {
            exc.getStackTrace();
            throw new RuntimeException("Cannot load property for configuration database");
        }
    }

}
