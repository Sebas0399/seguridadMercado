package com.mercado.security.repository;

import com.mercado.security.entity.Canton;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CantonRepository extends MongoRepository<Canton,String> {
}
