package com.microservice.auth.infrastructure.repository;

import com.microservice.auth.infrastructure.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

}
