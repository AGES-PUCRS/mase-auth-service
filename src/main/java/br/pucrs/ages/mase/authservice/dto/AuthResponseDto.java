
package br.pucrs.ages.mase.authservice.dto;

import br.pucrs.ages.mase.authservice.model.Role;

public class AuthResponseDto {

	private String token;

	private String refreshToken;

	private Long expires;

	private Role role;

	public AuthResponseDto(String token, String refreshToken, Long expires, Role role) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.expires = expires;
		this.role = role;
	}

	public AuthResponseDto() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
