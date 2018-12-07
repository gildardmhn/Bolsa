package com.bolsa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsa.model.Conta;
import com.bolsa.model.Empresa;
import com.bolsa.model.Monitoramento;
import com.bolsa.model.Negociacao;
import com.bolsa.model.enums.Transacao;
import com.bolsa.repository.NegociacaoRepository;
import com.bolsa.service.NegociacaoService;

@Service
public class NegociacaoServiceImpl implements NegociacaoService {

	@Autowired
	private NegociacaoRepository negociacaoRepository;
	@Autowired
	private ContaServiceImpl contaServiceImpl;
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;
	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;

	@Override
	public Negociacao salvarNegociacao(Long id, Negociacao negociacao) {
		Conta conta = contaServiceImpl.listaContaPeloId(id);
		negociacao.setConta(conta);
		return negociacaoRepository.save(negociacao);
	}

	@Override
	public List<Negociacao> listaNegociacoes(Long id) {
		Conta conta = contaServiceImpl.listaContaPeloId(id);
		return negociacaoRepository.findByConta(conta);
	}

	@Override
	public Negociacao compra(Monitoramento monitoramento) {
		Empresa empresa = empresaServiceImpl.listaEmpresaPeloNome(monitoramento.getEmpresa());
		Conta conta = monitoramento.getConta();
		float acoes = conta.getSaldo() / empresa.getValorAcao();

		Negociacao negociacao = new Negociacao();
		negociacao.setTipoTransacao(Transacao.COMPRA);
		negociacao.setData(new Date());
		negociacao.setEmpresa(monitoramento.getEmpresa());
		negociacao.setQuantidadeAcoes(acoes);
		negociacao.setValor(empresa.getValorAcao());
		conta.setSaldo(0);
		conta.setNumeroAcoes(acoes);
		contaServiceImpl.salvarConta(conta);
		return salvarNegociacao(conta.getId(), negociacao);
	}

	@Override
	public Negociacao venda(Monitoramento monitoramento) {
		Empresa empresa = empresaServiceImpl.listaEmpresaPeloNome(monitoramento.getEmpresa());
		Conta conta = monitoramento.getConta();

		Negociacao negociacao = new Negociacao();
		negociacao.setValor(empresa.getValorAcao());
		negociacao.setTipoTransacao(Transacao.VENDA);
		negociacao.setData(new Date());
		negociacao.setEmpresa(monitoramento.getEmpresa());
		negociacao.setQuantidadeAcoes(conta.getNumeroAcoes());
		conta.setSaldo(conta.getNumeroAcoes() * empresa.getValorAcao());
		conta.setNumeroAcoes(0);
		contaServiceImpl.salvarConta(conta);
		return salvarNegociacao(conta.getId(), negociacao);
	}

	@Override
	public void compraVendaAcoes(Long id) {
		List<Monitoramento> monitoramentos = monitoramentoServiceImpl.monitoramentosPelaConta(id);
		for (Monitoramento monitoramento : monitoramentos) {
			Empresa empresa = empresaServiceImpl.listaEmpresaPeloNome(monitoramento.getEmpresa());
			if (monitoramento.getConta().getSaldo() > 0) {
				if (monitoramento.getPrecoCompra() >= empresa.getValorAcao()) {
					compra(monitoramento);
				}
			} else if (monitoramento.getConta().getNumeroAcoes() > 0) {
				if (empresa.getValorAcao() >= monitoramento.getPrecoVenda()) {
					venda(monitoramento);
				}
			}
		}
	}

}
