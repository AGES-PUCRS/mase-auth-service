package br.pucrs.ages.mase.authservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.exception.UnauthorizedException;
import br.pucrs.ages.mase.authservice.model.Role;
import br.pucrs.ages.mase.authservice.service.RegisterService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class RegisterController {

        @Autowired
        private RegisterService registerService;

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public Mono<ResponseEntity<Object>> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
                if (registerRequestDto.getRole().equals(Role.CIVIL_DEFENSE)
                                || registerRequestDto.getRole().equals(Role.ADMIN)) {
                        throw new UnauthorizedException("Não autorizado para criar um " + registerRequestDto.getRole());
                }
                return this.insertStrategy(registerRequestDto);
        }

        @RequestMapping(value = "/register/civildefense", method = RequestMethod.POST)
        @PreAuthorize("hasAnyRole('ADMIN', 'CIVIL_DEFENSE')")
        public Mono<ResponseEntity<Object>> registerCivilDefense(
                        @Valid @RequestBody RegisterRequestDto registerRequestDto) {
                registerRequestDto.setRole(Role.CIVIL_DEFENSE);
                return this.insertStrategy(registerRequestDto);
        }

        private Mono<ResponseEntity<Object>> insertStrategy(RegisterRequestDto registerRequestDto) {
                return registerService.insert(registerRequestDto)
                                .map(auth -> ResponseEntity.status(HttpStatus.CREATED).build())
                                .onErrorReturn(UnauthorizedException.class,
                                                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
}