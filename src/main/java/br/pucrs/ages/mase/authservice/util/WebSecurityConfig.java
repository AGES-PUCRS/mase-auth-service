package br.pucrs.ages.mase.authservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private AuthManager authManager;

	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		return http.exceptionHandling().authenticationEntryPoint((swe, e) -> {
			return Mono.fromRunnable(() -> {
				swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			});
		}).accessDeniedHandler((swe, e) -> {
			return Mono.fromRunnable(() -> {
				swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
			});
		}).and().csrf().disable().formLogin().disable().httpBasic().disable().authenticationManager(authManager)
				.securityContextRepository(securityContextRepository).authorizeExchange()
				.pathMatchers(HttpMethod.OPTIONS).permitAll().pathMatchers("/v1/login").permitAll()
				.pathMatchers("/v1/refresh").permitAll().pathMatchers("/v1/register").permitAll()
				.pathMatchers("/v1/register/civildefense").permitAll()
				.pathMatchers("/v1/role").permitAll().anyExchange().authenticated().and().build();

	}
}