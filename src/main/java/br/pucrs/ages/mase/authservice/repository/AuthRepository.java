package br.pucrs.ages.mase.authservice.repository;

import br.pucrs.ages.mase.authservice.entity.AuthEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthRepository extends ReactiveMongoRepository<AuthEntity, ObjectId> {

    public Mono<AuthEntity> findOneByEmail(String email);

}
