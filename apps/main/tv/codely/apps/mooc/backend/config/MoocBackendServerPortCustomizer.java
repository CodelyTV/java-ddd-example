package tv.codely.apps.mooc.backend.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;

@Component
public final class MoocBackendServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	private final Parameter param;

	public MoocBackendServerPortCustomizer(Parameter param) {
		this.param = param;
	}

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		try {
			factory.setPort(param.getInt("MOOC_BACKEND_SERVER_PORT"));
		} catch (ParameterNotExist parameterNotExist) {
			parameterNotExist.printStackTrace();
		}
	}
}
