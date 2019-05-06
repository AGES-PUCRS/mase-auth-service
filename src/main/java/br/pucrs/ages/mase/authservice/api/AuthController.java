package br.pucrs.ages.mase.authservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.ages.mase.authservice.dto.AuthRequestDto;
import br.pucrs.ages.mase.authservice.dto.RefreshRequestDto;
import br.pucrs.ages.mase.authservice.service.AuthService;
import reactor.core.publisher.Mono;
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequestDto authRequestDto) {
		return authService.authenticate(authRequestDto);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody RefreshRequestDto refreshRequestDto) {
		return authService.refresh(refreshRequestDto);
	}


}
