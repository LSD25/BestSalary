package ua.com.salary.db.config.prod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
@Configuration
@Conditional(value = ProductionCondition.class)
@PropertySource(value = "classpath:/db/config/prod/production.properties")
public class ProductionConfiguration {

    public ProductionConfiguration() {
        super();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
