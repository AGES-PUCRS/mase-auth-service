
package br.pucrs.ages.mase.authservice.dto;

public class AuthResponseDto {

	private String token;

	private String refreshToken;

	private Long expires;

	public AuthResponseDto(String token, String refreshToken, Long expires) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.expires = expires;
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
}
