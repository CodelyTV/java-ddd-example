package tv.codely.apps.backoffice.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tv.codely.apps.backoffice.backend.middleware.BasicHttpAuthMiddleware;
import tv.codely.shared.domain.bus.command.CommandBus;

@Configuration
public class BackofficeBackendServerConfiguration {

	private final CommandBus bus;

	public BackofficeBackendServerConfiguration(CommandBus bus) {
		this.bus = bus;
	}

	@Bean
	public FilterRegistrationBean<BasicHttpAuthMiddleware> basicHttpAuthMiddleware() {
		FilterRegistrationBean<BasicHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new BasicHttpAuthMiddleware(bus));
		registrationBean.addUrlPatterns("/health-check");

		return registrationBean;
	}
}
