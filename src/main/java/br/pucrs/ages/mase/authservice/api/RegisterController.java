package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.pucrs.ages.mase.authservice.dto.AuthRequestDto;
import br.pucrs.ages.mase.authservice.dto.AuthResponseDto;
import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.entity.AuthEntity;
import br.pucrs.ages.mase.authservice.model.Auth;
import br.pucrs.ages.mase.authservice.service.AuthService;
import br.pucrs.ages.mase.authservice.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @ApiOperation(value = "API para registrar usuário", notes = "Faz a inclusão de um voluntário no usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inclusão de voluntário realizada com sucesso", response = Void.class), })
    @PostMapping("/register")
    public Mono<ResponseEntity<Object>> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return registerService.insert(registerRequestDto).map(auth -> {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        });
    }

}