package com.bolsa.service;

import java.util.List;

import com.bolsa.model.Empresa;

public interface EmpresaService {

	Empresa salvarEmpresa(Empresa empresa);

	Empresa buscaEmpresaPeloId(Long id);

	void deletarEmpresa(Long id);

	List<Empresa> listarEmpresas();

	Empresa atualizarEmpresa(Long id, Empresa empresa);

	Empresa listaEmpresaPeloNome(String nome);

}
