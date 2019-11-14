package tv.codely.mooc.shared.infrastructure.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tv.codely.shared.infrastructure.hibernate.HibernateConfigurationFactory;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MoocHibernateConfiguration {
    private final HibernateConfigurationFactory factory = new HibernateConfigurationFactory();

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        return factory.sessionFactory("mooc", dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return factory.dataSource("localhost", 3306, "mooc", "root", "");
    }
}
