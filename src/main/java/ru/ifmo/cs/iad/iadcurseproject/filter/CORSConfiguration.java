package ru.ifmo.cs.iad.iadcurseproject.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CORSConfiguration {
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		if (("http://localhost:3000".equals(req.getParameter("Origin")))) {
//			HttpServletResponse response = (HttpServletResponse) res;
//			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//			response.setHeader("Access-Control-Max-Age", "3600");
//			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//			chain.doFilter(req, res);
//		} else chain.doFilter(req, res);
//	}
//
//	public void init(FilterConfig filterConfig) {}
//
//	public void destroy() {}

}