package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.pucrs.ages.mase.authservice.dto.AuthRequestDto;
import br.pucrs.ages.mase.authservice.dto.RefreshRequestDto;
import br.pucrs.ages.mase.authservice.exception.UnauthorizedException;
import br.pucrs.ages.mase.authservice.service.AuthService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<?> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
		return authService.authenticate(authRequestDto).doOnError(UnauthorizedException.class, throwable -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});

	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public Mono<?> refresh(@Valid @RequestBody RefreshRequestDto refreshRequestDto) {
		return authService.refresh(refreshRequestDto).doOnError(UnauthorizedException.class, throwable -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}
}
