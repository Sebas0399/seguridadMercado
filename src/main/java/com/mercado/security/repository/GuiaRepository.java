package com.mercado.security.repository;

import com.mercado.security.entity.Guia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GuiaRepository extends MongoRepository<Guia,String> {
    @Query("{ 'fecha': { $gte: ?0, $lte: ?1 } }")
    List<Guia> findGuiasByFechas(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);
}
