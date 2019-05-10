package br.pucrs.ages.mase.authservice.util;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.pucrs.ages.mase.authservice.dto.AuthResponseDto;
import br.pucrs.ages.mase.authservice.model.Auth;

@Component
public class JWTUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${springbootwebfluxjjwt.jjwt.secret}")
	private String secret;

	@Value("${springbootwebfluxjjwt.jjwt.expiration}")
	private String expirationTime;

	@Value("${springbootwebfluxjjwt.jjwt.refreshTime}")
	private String refreshTime;

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes())).parseClaimsJws(token)
				.getBody();
	}

	public String getEmailFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public AuthResponseDto generateAuthResponse(Auth auth) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", auth.getRole());
		claims.put("userId", auth.getUserId());
		return generateAuthResponse(claims, auth.getEmail());
	}

	private AuthResponseDto generateAuthResponse(Map<String, Object> claims, String email) {
		final Date createdDate = new Date();
		final Date expirationData = generateExpirationDate(createdDate, false);
		final Date refreshLife = generateExpirationDate(createdDate, false);
		return new AuthResponseDto(doGenerateToken(claims, email, createdDate, expirationData),
				doGenerateToken(claims, email, createdDate, refreshLife), expirationData.getTime());
	}

	private Date generateExpirationDate(Date createdDate, boolean isRefreshToken) {
		final long lifeTime = Long.parseLong(isRefreshToken ? expirationTime : refreshTime);
		final Date expirationDate = new Date(createdDate.getTime() + lifeTime * 1000);
		return expirationDate;
	}

	private String doGenerateToken(Map<String, Object> claims, String email, Date createdDate, Date expirationDate) {

		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(createdDate).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes())).compact();
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}
