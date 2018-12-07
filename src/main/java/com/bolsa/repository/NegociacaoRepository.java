package com.bolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsa.model.Conta;
import com.bolsa.model.Negociacao;

@Repository
public interface NegociacaoRepository extends JpaRepository<Negociacao, Long> {

	List<Negociacao> findByConta(Conta conta);
}
