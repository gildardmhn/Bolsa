package com.bolsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsa.model.Monitoramento;
import com.bolsa.service.impl.MonitoramentoServiceImpl;
import com.bolsa.views.ContaView.MonitoramentoView;
import com.bolsa.views.ContaView.TodosOsContatos;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/app/monitoramento")
public class MonitoramentoController {

	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;

	@JsonView(TodosOsContatos.class)
	@PostMapping("{id}")
	public ResponseEntity<Monitoramento> salvarMonitoramentp(@PathVariable("id") Long id,
			@RequestBody Monitoramento monitoramento) {

		Monitoramento monitoramentoSalvo = monitoramentoServiceImpl.salvarMonitoramento(id, monitoramento);
		if (monitoramentoSalvo != null) {
			return new ResponseEntity<>(monitoramento, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluirMonitoramento(@PathVariable("id") Long id) {
		monitoramentoServiceImpl.deletarMonitoramento(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*@GetMapping("{id}")
	@JsonView(MonitoramentoView.class)
	public ResponseEntity<Monitoramento> listaMonitoramentoPeloId(@PathVariable("id") Monitoramento monitoramento) {
		return new ResponseEntity<>(monitoramento, HttpStatus.OK);
	}*/

	@PutMapping("{id}")
	@JsonView(TodosOsContatos.class)
	public ResponseEntity<Monitoramento> atualizarMonitoramento(@PathVariable("id") Long id,
			@RequestBody Monitoramento monitoramento) {
		monitoramentoServiceImpl.salvarMonitoramento(id, monitoramento);
		return new ResponseEntity<>(monitoramento, HttpStatus.CREATED);
	}
	
	@GetMapping("/conta/{id}")
	@JsonView(MonitoramentoView.class)
	public ResponseEntity<List<Monitoramento>> monitoramentosDaConta(@PathVariable("id") Long id) {
		List<Monitoramento> monitoramentos = monitoramentoServiceImpl.monitoramentosPelaConta(id);
		return new ResponseEntity<>(monitoramentos, HttpStatus.OK);
	}
}
