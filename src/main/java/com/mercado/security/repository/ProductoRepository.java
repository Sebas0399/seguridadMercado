package com.mercado.security.repository;

import com.mercado.security.entity.Producto;
import com.mercado.security.entity.Usuario;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends MongoRepository<Producto,String> {


}
