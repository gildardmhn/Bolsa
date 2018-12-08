package com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsa.model.Conta;
import com.bolsa.repository.ContaRepository;
import com.bolsa.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public Conta salvarConta(Conta conta) {
		return contaRepository.save(conta);
	}

	@Override
	public Conta listaContaPeloId(Long id) {
		return contaRepository.getOne(id);
	}

	@Override
	public List<Conta> listaContas() {
		return contaRepository.findAll();
	}

	@Override
	public void deleteContaById(Long id) {
		contaRepository.deleteById(id);
	}

	@Override
	public float saldoInicial(Long id) {
		return listaContaPeloId(id).getSaldo();
	}

}
