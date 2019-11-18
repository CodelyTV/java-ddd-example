package tv.codely.mooc.shared.infrastructure.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;
import tv.codely.shared.infrastructure.hibernate.HibernateConfigurationFactory;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class MoocHibernateConfiguration {
    private final HibernateConfigurationFactory factory;
    private final Parameter                     config;
    private final String                        CONTEXT_NAME = "mooc";

    public MoocHibernateConfiguration(HibernateConfigurationFactory factory, Parameter config) {
        this.factory = factory;
        this.config  = config;
    }

    @Bean("mooc-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException, ParameterNotExist {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("mooc-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException, ParameterNotExist {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("mooc-data_source")
    public DataSource dataSource() throws IOException, ParameterNotExist {
        return factory.dataSource(
            config.get("MOOC_DATABASE_HOST"),
            config.getInt("MOOC_DATABASE_PORT"),
            config.get("MOOC_DATABASE_NAME"),
            config.get("MOOC_DATABASE_USER"),
            config.get("MOOC_DATABASE_PASSWORD")
        );
    }
}
