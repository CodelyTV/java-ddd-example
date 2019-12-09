package tv.codely.apps.backoffice.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.codely.shared.infrastructure.spring.BasicHttpAuthMiddleware;

@Configuration
public class BackofficeBackendServerConfiguration {
    @Bean
    public FilterRegistrationBean<BasicHttpAuthMiddleware> basicHttpAuthMiddleware() {
        FilterRegistrationBean<BasicHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BasicHttpAuthMiddleware());
        registrationBean.addUrlPatterns("/health-check");

        return registrationBean;
    }
}
