package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @ApiOperation(value = "API para registrar usuário", notes = "Faz a inclusão de um voluntário no usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inclusão de voluntário realizada com sucesso", response = Void.class),})
    @PostMapping("/register")
    public Mono<ResponseEntity<Object>> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return registerService.insert(registerRequestDto)
                .map(auth -> ResponseEntity.status(HttpStatus.CREATED).build())
                .onErrorReturn(Exception.class, ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}