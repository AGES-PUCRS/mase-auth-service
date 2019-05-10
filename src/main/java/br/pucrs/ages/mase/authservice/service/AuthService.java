package br.pucrs.ages.mase.authservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.pucrs.ages.mase.authservice.dto.AuthRequestDto;
import br.pucrs.ages.mase.authservice.dto.AuthResponseDto;
import br.pucrs.ages.mase.authservice.dto.RefreshRequestDto;
import br.pucrs.ages.mase.authservice.entity.AuthEntity;
import br.pucrs.ages.mase.authservice.model.Auth;
import br.pucrs.ages.mase.authservice.repository.AuthRepository;
import br.pucrs.ages.mase.authservice.util.JWTUtil;
import br.pucrs.ages.mase.authservice.util.PWDEncoder;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PWDEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public Mono<ResponseEntity<?>> authenticate(AuthRequestDto request) {
        return authRepository.findOneByEmail(request.getEmail())
                .map(authEntity -> objectMapper.convertValue(authEntity, Auth.class)).map((auth) -> {
                    if (passwordEncoder.encode(request.getPassword()).equals(auth.getPassword())) {
                        return ResponseEntity.ok(jwtUtil.generateAuthResponse(auth));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    public Mono<ResponseEntity<AuthResponseDto>> refresh(RefreshRequestDto request) {
        if (jwtUtil.validateToken(request.getRefreshToken())) {
            return authRepository.findOneByEmail(jwtUtil.getEmailFromToken(request.getRefreshToken()))
                    .map(authEntity -> objectMapper.convertValue(authEntity, Auth.class)).map((auth) -> {
                        return ResponseEntity.ok(jwtUtil.generateAuthResponse(auth));
                    }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

        } else
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
