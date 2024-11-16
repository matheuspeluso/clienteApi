package br.com.cotiinformatica.configurations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.cotiinformatica.filters.JwtTokenFilter;

@Configuration
public class JwtTokenConfig {
	@Bean
	public FilterRegistrationBean<JwtTokenFilter> jwtFilter() {
		FilterRegistrationBean<JwtTokenFilter> filter = new FilterRegistrationBean<JwtTokenFilter>();
		filter.setFilter(new JwtTokenFilter());
		filter.addUrlPatterns("/api/clientes/*");
		return filter;
	}
}



