package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.exception.UnauthorizedException;
import br.pucrs.ages.mase.authservice.service.RegisterService;
import reactor.core.publisher.Mono;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Mono<ResponseEntity<Object>> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return registerService.insert(registerRequestDto).map(auth -> ResponseEntity.status(HttpStatus.CREATED).build())
                .onErrorReturn(UnauthorizedException.class, ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}