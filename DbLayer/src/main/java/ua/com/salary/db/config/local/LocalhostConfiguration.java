package ua.com.salary.db.config.local;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
@Configuration
@Conditional(value = LocalhostCondition.class)
@PropertySource(value = "classpath:/db/config/local/local.properties")
public class LocalhostConfiguration {

    public LocalhostConfiguration() {
        super();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
