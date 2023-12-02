package tv.codely.apps.mooc.backend.config;

import java.util.Optional;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import tv.codely.shared.infrastructure.spring.ApiExceptionMiddleware;

@Configuration
public class MoocBackendServerConfiguration {

	private final Optional<RequestMappingHandlerMapping> mapping;

	public MoocBackendServerConfiguration(Optional<RequestMappingHandlerMapping> mapping) {
		this.mapping = mapping;
	}

	@Bean
	public FilterRegistrationBean<ApiExceptionMiddleware> apiExceptionMiddleware() {
		FilterRegistrationBean<ApiExceptionMiddleware> registrationBean = new FilterRegistrationBean<>();

		mapping.ifPresent(map -> registrationBean.setFilter(new ApiExceptionMiddleware(map)));

		return registrationBean;
	}
}
