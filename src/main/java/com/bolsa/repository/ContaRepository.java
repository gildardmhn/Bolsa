package com.bolsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsa.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
