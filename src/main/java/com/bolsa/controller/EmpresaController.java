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

import com.bolsa.model.Empresa;
import com.bolsa.service.impl.EmpresaServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaServiceImpl empresaServiceImpl;

	@ApiOperation(value = "Criação de uma nova empresa", notes = "Este controller"
			+ " serve para a criação de uma nova empresa")
	@PostMapping
	public ResponseEntity<Empresa> salvarEmpresa(@RequestBody Empresa empresa) {
		Empresa empresaNew = empresaServiceImpl.salvarEmpresa(empresa);
		if(empresaNew != null) {
			return new ResponseEntity<Empresa>(empresa, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "Listagem de uma empresa", notes = "Este controller"
			+ " serve para listar uma empresa informando o seu id")
	@GetMapping("{id}")
	public ResponseEntity<Empresa> listaEmpresaPeloId(@PathVariable("id") Empresa empresa) {
		return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
	}

	@ApiOperation(value = "Listagem de todas as empresas", notes = "Este controller"
			+ " serve para listar todas as empresas cadastradas no sistema")
	@GetMapping
	public ResponseEntity<List<Empresa>> listaEmpresas() {
		List<Empresa> empresas = empresaServiceImpl.listarEmpresas();
		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}

	@ApiOperation(value = "Remover uma empresa", notes = "Este controller"
			+ " serve para remover uma empresa do sistema informando o seu id")
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarEmpresa(@PathVariable("id") Long id) {
		empresaServiceImpl.deletarEmpresa(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Atualizar dados de uma empresa", notes = "Este controller"
			+ " serve para atualizar os dados de uma empresa informando o seu id")
	@PutMapping("{id}")
	public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
		empresaServiceImpl.atualizarEmpresa(id, empresa);
		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}
}
