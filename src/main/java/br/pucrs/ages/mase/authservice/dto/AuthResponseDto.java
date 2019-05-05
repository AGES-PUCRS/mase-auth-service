
package br.pucrs.ages.mase.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AuthResponseDto {

	private String token;

	private String refreshToken;	

	private Long expires;	
}

