
package br.pucrs.ages.mase.authservice.util;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.pucrs.ages.mase.authservice.model.Role;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthManager implements ReactiveAuthenticationManager {

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	@SuppressWarnings("unchecked")
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();

		String username;
		try {
			username = jwtUtil.getEmailFromToken(authToken);
		} catch (Exception e) {
			username = null;
		}
		if (username != null && jwtUtil.validateToken(authToken)) {
			Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
			String role = claims.get("role", String.class);
			List<Role> roles = new ArrayList<>();
			roles.add(Role.valueOf(role));
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
							.collect(Collectors.toList()));
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}
}
