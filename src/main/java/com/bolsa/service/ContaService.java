package com.bolsa.service;

import java.util.List;

import com.bolsa.model.Conta;

public interface ContaService {

	Conta salvarConta(Conta conta);

	Conta listaContaPeloId(Long id);

	List<Conta> listaContas();

	void deleteContaById(Long id);
}
