package br.com.cotiinformatica.filters;
import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	        throws IOException, ServletException {
	    final HttpServletRequest request = (HttpServletRequest) servletRequest;
	    final HttpServletResponse response = (HttpServletResponse) servletResponse;

	    // Permite que as requisições OPTIONS passem sem validação JWT
	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        filterChain.doFilter(request, response);
	        return;
	    }

	    final String authHeader = request.getHeader("Authorization");
	    
	    // Validação do header Authorization
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
	        return;
	    }

	    try {
	        String jwtSecret = "73cbce54-24dd-46af-a820-4e8fa5f8ecc2";
	        final String token = authHeader.substring(7);
	        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	        request.setAttribute("claims", claims);
	        filterChain.doFilter(request, response); // Continua o processamento se tudo estiver OK
	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado.");
	    }
	}

}




