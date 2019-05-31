package br.pucrs.ages.mase.authservice.service;

import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.model.Auth;
import br.pucrs.ages.mase.authservice.repository.AuthRepository;
import br.pucrs.ages.mase.authservice.service.RegisterService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import org.junit.runner.RunWith;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RegisterServiceTest {

    @Autowired(required = true)
    private RegisterService registerService;

    private AuthRepository authRepository;

    public RegisterServiceTest() {
        authRepository = mock(AuthRepository.class);
    }

    @Test
    public void shouldInsertCivilDefenseInstitution() {
        RegisterRequestDto requestDto = EnhancedRandom.random(RegisterRequestDto.class);
        when(authRepository.save(any())).thenAnswer(response -> Mono.just(response.getArgument(0)));
        StepVerifier.create(registerService.insert(requestDto)).assertNext(response -> {
            assertEquals(Auth.class, response.getClass());
        }).verifyComplete();

    }
}
