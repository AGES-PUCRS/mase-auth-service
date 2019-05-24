package br.pucrs.ages.mase.authservice.service;

import br.pucrs.ages.mase.authservice.dto.RegisterRequestDto;
import br.pucrs.ages.mase.authservice.entity.AuthEntity;
import br.pucrs.ages.mase.authservice.model.Auth;
import br.pucrs.ages.mase.authservice.repository.AuthRepository;
import br.pucrs.ages.mase.authservice.util.PWDEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class RegisterService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PWDEncoder passwordEncoder;

    public Mono<Auth> insert(RegisterRequestDto request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return authRepository.save(objectMapper.convertValue(request, AuthEntity.class))
                .subscribeOn(Schedulers.elastic())
                .map(authRepository -> objectMapper.convertValue(authRepository, Auth.class)).doOnError(exception -> {
                    throw new RuntimeException(exception);
                });
    }

}
