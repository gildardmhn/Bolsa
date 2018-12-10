package com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsa.model.Conta;
import com.bolsa.model.Monitoramento;
import com.bolsa.model.Negociacao;
import com.bolsa.repository.ContaRepository;
import com.bolsa.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;
	@Autowired
	private NegociacaoServiceImpl negociacaoServiceImpl;

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
		Conta conta = listaContaPeloId(id);
		List<Monitoramento> monitoramentos = conta.getMonitoramentos();
		List<Negociacao> negociacoes = conta.getNegociacoes();
		for (Negociacao negociacao : negociacoes) {
			negociacaoServiceImpl.deletarNegociacao(negociacao.getId());
		}
		for (Monitoramento monitoramento : monitoramentos) {
			monitoramentoServiceImpl.deletarMonitoramento(monitoramento.getId());
		}
		contaRepository.deleteById(id);
	}

	@Override
	public float saldoInicial(Long id) {
		return listaContaPeloId(id).getSaldo();
	}

	@Override
	public Conta atualizarConta(Long id, Conta conta) {
		Conta contaUpdated = listaContaPeloId(id);
		contaUpdated.setEmail(conta.getEmail());
		return contaRepository.save(contaUpdated);
	}

	@Override
	public Conta depositar(Long id, Conta conta) {
		Conta contaDeposito = listaContaPeloId(id);
		if(conta.getSaldo() > 0) {
			contaDeposito.setSaldo(contaDeposito.getSaldo() + conta.getSaldo());
			return contaRepository.save(contaDeposito);
		}
		return null;
	}

}
