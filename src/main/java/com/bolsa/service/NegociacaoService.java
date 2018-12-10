package com.bolsa.service;

import java.util.List;

import com.bolsa.model.Monitoramento;
import com.bolsa.model.Negociacao;

public interface NegociacaoService {

	Negociacao salvarNegociacao(Long id, Negociacao negociacao);

	List<Negociacao> listaNegociacoes(Long id);
	
	Negociacao compra(Monitoramento monitoramento);
	
	Negociacao venda(Monitoramento monitoramento);

	void compraVendaAcoes(Long id);
	
	void historicoNegociacoes(Long id);
	
	void deletarNegociacao(Long id);

}
