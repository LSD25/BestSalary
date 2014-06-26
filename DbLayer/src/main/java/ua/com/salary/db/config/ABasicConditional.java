package ua.com.salary.db.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Victor Zagnitko Виктор on 17.06.2014.
 */
public abstract class ABasicConditional implements Condition {

    protected static final String ENVIRONMENT = "BEST_SALARY_CONFIG";

    @Override
    public abstract boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);

}
