package com.bolsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsa.model.Conta;
import com.bolsa.service.impl.ContaServiceImpl;
import com.bolsa.views.ContaView.Contato;
import com.bolsa.views.ContaView.TodasAsContas;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/app/conta")
public class ContaController {

	@Autowired
	private ContaServiceImpl contaServiceImpl;

	@PostMapping
	public ResponseEntity<Conta> salvarConta(@RequestBody Conta conta) {
		contaServiceImpl.salvarConta(conta);
		return new ResponseEntity<>(conta, HttpStatus.CREATED);
	}

	@JsonView(Contato.class)
	@GetMapping("{id}")
	public ResponseEntity<Conta> listaContaPeloId(@PathVariable("id") Conta conta) {
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}
	
	@JsonView(TodasAsContas.class)
	@GetMapping
	public ResponseEntity<List<Conta>> listaContas(){
		List<Conta> contas = contaServiceImpl.listaContas();
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}

}
