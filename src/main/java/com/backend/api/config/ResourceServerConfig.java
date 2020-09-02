package com.backend.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("petApiId").stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
			http.authorizeRequests()
					.antMatchers("/h2/*", "/v2/api-docs", "/swagger-ui.html", "/configuration/ui",
							"/swagger-resources/**", "/configuration/**", "/webjars/**")
					.permitAll();
		}
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/save").permitAll()
				.antMatchers(HttpMethod.GET, "/users/list").hasAnyRole("ADMIN").anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
	}

	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}

}
