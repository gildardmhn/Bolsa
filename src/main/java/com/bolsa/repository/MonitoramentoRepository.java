package com.bolsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsa.model.Conta;
import com.bolsa.model.Monitoramento;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
	List<Monitoramento> findByConta(Conta conta);
}
