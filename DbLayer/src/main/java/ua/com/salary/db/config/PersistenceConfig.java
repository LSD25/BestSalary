package ua.com.salary.db.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Vicotr Zagnitko on 18.06.2014.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    private static final String PACKAGE_TO_SCAN = "ua.com.salary.db.entity";

    @Value(value = "${url}")
    private String mUrl;

    @Value(value = "${driver}")
    private String mDriverClassName;

    @Value(value = "${username_}")
    private String mUserName;

    @Value(value = "${password}")
    private String mPassword;

    @Value(value = "${hbm2ddl}")
    private String mHbm2ddl;

    @Value(value = "${dialect}")
    private String mDialect;

    @Value(value = "${connection.pool.size}")
    private String mConnectionPoolSize;

    @Value(value = "${connection.pool.max.active}")
    private String mMaxActiveConnection;

    private Properties mProperties;

    public PersistenceConfig() {
        super();
    }

    @PostConstruct
    public void initResources() {
        this.mProperties = new Properties();
        this.mProperties.setProperty("hibernate.hbm2ddl.auto", this.mHbm2ddl);
        this.mProperties.setProperty("hibernate.dialect", this.mDialect);
        this.mProperties.setProperty("hibernate.show_sql", "true");
        this.mProperties.setProperty("connection.pool_size", this.mConnectionPoolSize);
        this.mProperties.setProperty("maxActive", this.mMaxActiveConnection);
        this.mProperties.setProperty("format_sql", "true");
        this.mProperties.setProperty("current_session_context_class", "thread");
        //Hibernate Envers
        this.mProperties.setProperty("hibernate.ejb.event.post-insert", "org.hibernate.ejb.event.EJB3PostInsertEventListener," +
                "org.hibernate.envers.event.AuditEventListener");
        this.mProperties.setProperty("hibernate.ejb.event.post-update", "org.hibernate.ejb.event.EJB3PostUpdateEventListener," +
                "org.hibernate.envers.event.AuditEventListener");
        this.mProperties.setProperty("hibernate.ejb.event.post-delete", "org.hibernate.ejb.event.EJB3PostDeleteEventListener," +
                "org.hibernate.envers.event.AuditEventListener");
        this.mProperties.setProperty("hibernate.ejb.event.pre-collection-update", "org.hibernate.envers.event.AuditEventListener");
        this.mProperties.setProperty("hibernate.ejb.event.pre-collection-remove", "org.hibernate.envers.event.AuditEventListener");
        this.mProperties.setProperty("hibernate.ejb.event.post-collection-recreate", "org.hibernate.envers.event.AuditEventListener");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(this.mProperties);
        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.mDriverClassName);
        dataSource.setUrl(this.mUrl);
        dataSource.setUsername(this.mUserName);
        dataSource.setPassword(this.mPassword);
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

}
