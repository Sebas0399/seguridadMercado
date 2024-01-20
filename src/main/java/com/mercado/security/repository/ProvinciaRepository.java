package com.mercado.security.repository;

import com.mercado.security.entity.Provincia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProvinciaRepository extends MongoRepository<Provincia,String> {
}
