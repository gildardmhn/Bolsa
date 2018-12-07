package com.bolsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsa.service.impl.EmpresaServiceImpl;
import com.bolsa.service.impl.NegociacaoServiceImpl;

@RestController
@RequestMapping("/app/start")
public class BolsaController {

	@Autowired
	private NegociacaoServiceImpl negociacaoServiceImpl;
	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;

	@PostMapping("{id}")
	public void start(@PathVariable("id") Long id) {
		for (int i = 0; i < 100; i++) {
			empresaServiceImpl.SimuladorConexaoPreco();
			negociacaoServiceImpl.compraVendaAcoes(id);
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
}
