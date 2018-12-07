package com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsa.model.Conta;
import com.bolsa.model.Empresa;
import com.bolsa.model.Monitoramento;
import com.bolsa.repository.MonitoramentoRepository;
import com.bolsa.service.MonitoramentoService;

@Service
public class MonitoramentoServiceImpl implements MonitoramentoService {

	@Autowired
	private MonitoramentoRepository monitoramentoRepository;
	@Autowired
	private ContaServiceImpl contaServiceImpl;
	@Autowired
	EmpresaServiceImpl empresaServiceImpl;

	@Override
	public Monitoramento salvarMonitoramento(Long id, Monitoramento monitoramento) {
		/*
		 * Pode registrar um monitoramento com o mesmo nome somente uma vez por conta e
		 * não pode registrar um monitormento que não tenha o nome de uma empresa
		 */
		Conta conta = contaServiceImpl.listaContaPeloId(id);
		monitoramento.setConta(conta);
		List<Empresa> empresas = empresaServiceImpl.listarEmpresas();
		for (Empresa empresa : empresas) {
			if (monitoramento.getEmpresa().equals(empresa.getNome())) {
				List<Monitoramento> monitoramentosDaConta = monitoramentosPelaConta(conta.getId());
				if (monitoramentosDaConta.isEmpty()) {
					return monitoramentoRepository.save(monitoramento);
				} else {
					int cont = 0;
					for (Monitoramento monitoramento_ : monitoramentosDaConta) {
						if (monitoramento_.getEmpresa().equals(monitoramento.getEmpresa())) {
							cont++;
						}
					}
					if (cont > 0)
						return null;
					else
						return monitoramentoRepository.save(monitoramento);
				}

			}
		}
		return null;
	}

	@Override
	public void deletarMonitoramento(Long id) {
		monitoramentoRepository.deleteById(id);
	}

	@Override
	public Monitoramento listaMonitoramentoPeloId(Long id) {
		return monitoramentoRepository.getOne(id);
	}

	@Override
	public List<Monitoramento> listaMonitoramentos() {
		return monitoramentoRepository.findAll();
	}

	public List<Monitoramento> monitoramentosPelaConta(Long id) {
		Conta conta = contaServiceImpl.listaContaPeloId(id);
		return monitoramentoRepository.findByConta(conta);
	}

}
