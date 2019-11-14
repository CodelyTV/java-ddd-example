package tv.codely.shared.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfig {
    @Bean
    public Dotenv dotenv() {
        Dotenv dotenv = Dotenv
            .configure()
            .directory("/")
            .filename(".env")
            .load();

        return dotenv;
    }
}
