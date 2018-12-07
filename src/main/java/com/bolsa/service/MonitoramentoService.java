package com.bolsa.service;

import java.util.List;

import com.bolsa.model.Monitoramento;

public interface MonitoramentoService {

	Monitoramento salvarMonitoramento(Long id, Monitoramento monitoramento);
	List<Monitoramento> listaMonitoramentos();
	void deletarMonitoramento(Long id);
	Monitoramento listaMonitoramentoPeloId(Long id);
}
