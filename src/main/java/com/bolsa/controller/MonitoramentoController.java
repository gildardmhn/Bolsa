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
import com.bolsa.views.ContaView.Contato;
import com.bolsa.views.ContaView.MonitoramentoView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/monitoramento")
public class MonitoramentoController {

	@Autowired
	private MonitoramentoServiceImpl monitoramentoServiceImpl;

	@ApiOperation(value = "Criação de um monitoramento para uma conta", notes = "Este controller"
			+ " serve para criar um monitoramento para uma conta informando o id"
			+ " da conta e mandando um Json com os dados do monitoramento")
	@JsonView(Contato.class)
	@PostMapping("{id}")
	public ResponseEntity<Monitoramento> salvarMonitoramento(@PathVariable("id") Long id,
			@RequestBody Monitoramento monitoramento) {

		Monitoramento monitoramentoSalvo = monitoramentoServiceImpl.salvarMonitoramento(id, monitoramento);
		if (monitoramentoSalvo != null) {
			return new ResponseEntity<>(monitoramento, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "Deletar um monitoramento de uma conta", notes = "Este controller"
			+ " serve deletar um monitoramento referente a uma conta informando o id do monitoramento")
	@DeleteMapping("{id}")
	public ResponseEntity<?> excluirMonitoramento(@PathVariable("id") Long id) {
		monitoramentoServiceImpl.deletarMonitoramento(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Atualização de um monitoramento de uma conta", notes = "Este controller"
			+ " serve para atulizar os dados de um monitoramento")
	@PutMapping("{id}")
	@JsonView(Contato.class)
	public ResponseEntity<Monitoramento> atualizarMonitoramento(@PathVariable("id") Long id,
			@RequestBody Monitoramento monitoramento) {
		monitoramentoServiceImpl.salvarMonitoramento(id, monitoramento);
		return new ResponseEntity<>(monitoramento, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Listagem dos monitoramentos de uma conta", notes = "Este controller"
			+ " serve para listar todos os monitormentos de uma determinada conta")
	@GetMapping("/conta/{id}")
	@JsonView(MonitoramentoView.class)
	public ResponseEntity<List<Monitoramento>> monitoramentosDaConta(@PathVariable("id") Long id) {
		List<Monitoramento> monitoramentos = monitoramentoServiceImpl.monitoramentosPelaConta(id);
		return new ResponseEntity<>(monitoramentos, HttpStatus.OK);
	}
}
