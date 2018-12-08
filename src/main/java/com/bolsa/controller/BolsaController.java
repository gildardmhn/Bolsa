package com.bolsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsa.service.impl.EmpresaServiceImpl;
import com.bolsa.service.impl.NegociacaoServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/bolsa")
public class BolsaController {

	@Autowired
	private NegociacaoServiceImpl negociacaoServiceImpl;
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;

	@ApiOperation(value = "Inicia a application", notes = "Este controller inicia a simulação das compras e vendas de uma determinada conta")
	@PostMapping("/start/{id}")
	public void start(@PathVariable("id") Long id) {
		for (int i = 0; i < 100; i++) {
			empresaServiceImpl.SimuladorConexaoPreco();
			negociacaoServiceImpl.compraVendaAcoes(id);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		negociacaoServiceImpl.historicoNegociacoes(id);
	}
	
	@ApiOperation(value = "Histórico de uma conta", notes = "Este controller mostra o histórico de compras e vendas de uma determinada conta passando o seu id no terminal da applicação")
	@GetMapping("/historico/{id}")
	public void historico(@PathVariable("id") Long id) {
		negociacaoServiceImpl.historicoNegociacoes(id);
	}
}
