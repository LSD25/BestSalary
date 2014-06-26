package ua.com.salary.db.config.dev;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ua.com.salary.db.config.ABasicConditional;
import ua.com.salary.db.config.Environment;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
public class DevelopmentCondition extends ABasicConditional {

    public DevelopmentCondition() {
        super();
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return System.getenv().get(ENVIRONMENT).intern() == Environment.DEVELOPMNET.getEnv();
    }

}
