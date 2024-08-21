package tv.codely.apps.backoffice.frontend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;

@Configuration
@EnableWebMvc
public class BackofficeFrontendWebConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.freeMarker();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/backoffice_frontend/public/");
	}

	@Bean
	public ViewResolver getViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setSuffix(".ftl");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/backoffice_frontend/templates/");
		configurer.setDefaultEncoding("UTF-8");
		//        configurer.setFreemarkerVariables(new HashMap<String, Object>() {{
		//            put("flash", new Flash());
		//        }});

		return configurer;
	}

	@Primary
	@Bean
	public RabbitMqEventBus rabbitMqEventBus(
		RabbitMqPublisher publisher,
		@Qualifier("backofficeMysqlEventBus") MySqlEventBus failoverPublisher
	) {
		return new RabbitMqEventBus(publisher, failoverPublisher);
	}
}
