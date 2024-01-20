package com.mercado.security.repository;

import com.mercado.security.entity.Parroquia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParroquiaRepository extends MongoRepository<Parroquia,String> {
}
