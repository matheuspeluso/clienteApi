package br.com.cotiinformatica.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**") //qualquer endpoint
			.allowedOrigins("http://localhost:4200") //URL do projeto cliente (frontend)
			.allowedMethods("POST", "PUT", "DELETE", "GET") //métodos permitidos
			.allowedHeaders("*"); //permite enviar parametros de cabeçalho
	}
}
