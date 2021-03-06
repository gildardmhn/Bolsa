package com.bolsa.service.impl;

import java.time.LocalDateTime;
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
	@Autowired
	private EmailServiceImpl emailserviceImpl;

	@Override
	public Negociacao salvarNegociacao(Long id, Negociacao negociacao) {
		negociacao.setConta(contaServiceImpl.listaContaPeloId(id));
		return negociacaoRepository.save(negociacao);
	}

	@Override
	public List<Negociacao> listaNegociacoes(Long id) {
		return negociacaoRepository.findByConta(contaServiceImpl.listaContaPeloId(id));
	}

	@Override
	public Negociacao compra(Monitoramento monitoramento) {
		Empresa empresa = empresaServiceImpl.listaEmpresaPeloNome(monitoramento.getEmpresa());
		Conta conta = monitoramento.getConta();
		float acoes = conta.getSaldo() / empresa.getValorAcao();

		Negociacao negociacao = new Negociacao();
		negociacao.setTipoTransacao(Transacao.COMPRA);
		negociacao.setData(LocalDateTime.now());
		negociacao.setEmpresa(monitoramento.getEmpresa());
		negociacao.setQuantidadeAcoes(acoes);
		negociacao.setValor(empresa.getValorAcao());
		conta.setSaldo(0);
		conta.setNumeroAcoes(acoes);
		Negociacao neg = salvarNegociacao(conta.getId(), negociacao);
		contaServiceImpl.salvarConta(conta);

		emailserviceImpl.sendEmail(neg, monitoramento, empresa);
		return neg;
	}

	@Override
	public Negociacao venda(Monitoramento monitoramento) {
		Empresa empresa = empresaServiceImpl.listaEmpresaPeloNome(monitoramento.getEmpresa());
		Conta conta = monitoramento.getConta();

		Negociacao negociacao = new Negociacao();
		negociacao.setValor(empresa.getValorAcao());
		negociacao.setTipoTransacao(Transacao.VENDA);
		negociacao.setData(LocalDateTime.now());
		negociacao.setEmpresa(monitoramento.getEmpresa());
		negociacao.setQuantidadeAcoes(conta.getNumeroAcoes());
		conta.setSaldo(conta.getNumeroAcoes() * empresa.getValorAcao());
		conta.setNumeroAcoes(0);
		Negociacao neg = salvarNegociacao(conta.getId(), negociacao);
		contaServiceImpl.salvarConta(conta);

		emailserviceImpl.sendEmail(neg, monitoramento, empresa);
		return neg;
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

	@Override
	public void historicoNegociacoes(Long id) {
		Conta conta = contaServiceImpl.listaContaPeloId(id);
		List<Negociacao> negociacoes = conta.getNegociacoes();
		for (Negociacao negociacao : negociacoes) {
			System.out.println("------------------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-----------------------------------");
			System.out.println("Identificador: " + negociacao.getId());
			System.out.println("Transação : " + negociacao.getTipoTransacao());
			System.out.println("Empresa: " + negociacao.getEmpresa());
			System.out.println("Valor: " + negociacao.getValor());
			System.out.println("Quantidade de ações: " + negociacao.getQuantidadeAcoes());
			System.out.println("Data: " + negociacao.getData());
			System.out.println("------------------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("------------------------------------------");
			System.out.println("-----------------------------------");
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("---                                                      ---");
		System.out.println("---                                                      ---");
		System.out.println("---    Saldo Final: " + conta.getSaldo() + "             ---");
		System.out.println("---    Número de ações: " + conta.getNumeroAcoes() + "   ---");
		System.out.println("---                                                      ---");
		System.out.println("---                                                      ---");
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");
	}

	@Override
	public void deletarNegociacao(Long id) {
		negociacaoRepository.deleteById(id);
	}

}
