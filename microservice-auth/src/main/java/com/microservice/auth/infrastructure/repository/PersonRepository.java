package com.microservice.auth.infrastructure.repository;

import com.microservice.auth.infrastructure.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

    Optional<PersonEntity> findByUserName(String username);
}
