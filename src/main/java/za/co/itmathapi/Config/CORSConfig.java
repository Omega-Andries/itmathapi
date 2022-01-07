package za.co.itmathapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig {
	@Bean 
	public WebMvcConfigurer itmathConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("POST", "PUT", "GET", "DELETE").allowedHeaders("*").allowedOrigins("http://localhost:4200", "https://tsaomega-tech.web.app", "https://it-mathematics.web.app");
			}
		};
	}
}