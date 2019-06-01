package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.pucrs.ages.mase.authservice.dto.AuthRequestDto;
import br.pucrs.ages.mase.authservice.dto.AuthResponseDto;
import br.pucrs.ages.mase.authservice.dto.RefreshRequestDto;
import br.pucrs.ages.mase.authservice.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

	@ApiOperation(value = "API para autenticar usuário", notes = "Faz a autenticação de um usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Autenticação de usuário realizada com sucesso", response = AuthResponseDto.class), })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<?> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
		return authService.authenticate(authRequestDto).doOnError(Exception.class, throwable -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	@ApiOperation(value = "API para refrescar token usuário", notes = "Faz a refresca token de um usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Token refrescado com sucesso", response = AuthResponseDto.class), })
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public Mono<?> refresh(@Valid @RequestBody RefreshRequestDto refreshRequestDto) {
		return authService.refresh(refreshRequestDto).doOnError(Exception.class, throwable -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

}
