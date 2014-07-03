package ua.com.salary.db.config.prod;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ua.com.salary.db.config.ABasicConditional;
import ua.com.salary.db.config.Environment;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
public class ProductionCondition extends ABasicConditional {

    public ProductionCondition() {
        super();
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            return System.getenv().get(ENVIRONMENT).intern() == Environment.PRODUCTION.getEnv();
        } catch (Exception exc) {
            exc.getStackTrace();
            throw new RuntimeException("Cannot load property for configuration database");
        }
    }

}
