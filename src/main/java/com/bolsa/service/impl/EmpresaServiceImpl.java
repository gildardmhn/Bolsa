package com.bolsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsa.model.Empresa;
import com.bolsa.repository.EmpresaRepository;
import com.bolsa.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private RandomNumberImpl randomNumberImpl;

	@Override
	public Empresa salvarEmpresa(Empresa empresa) {
		List<Empresa> empresas = listarEmpresas();
		int cont = 0;
		for (Empresa empresa_ : empresas) {
			if (empresa_.getNome().equals(empresa.getNome())) {
				cont++;
			}
		}
		if (cont > 0) {
			return null;
		}
		return empresaRepository.save(empresa);
	}

	@Override
	public Empresa buscaEmpresaPeloId(Long id) {
		return empresaRepository.getOne(id);
	}

	@Override
	public void deletarEmpresa(Long id) {
		empresaRepository.deleteById(id);
	}

	@Override
	public List<Empresa> listarEmpresas() {
		return empresaRepository.findAll();
	}

	@Override
	public Empresa atualizarEmpresa(Long id, Empresa empresa) {
		empresa.setId(id);
		return empresaRepository.save(empresa);
	}

	@Override
	public Empresa listaEmpresaPeloNome(String nome) {
		return empresaRepository.findByNome(nome);
	}
	
	@Override
	public Empresa atualizarEmpresa(Long id) {
		return null;
	}
	
	public void SimuladorConexaoPreco() {
		List<Empresa> empresas = listarEmpresas();
		for(Empresa empresa : empresas) {
			empresa.setValorAcao(randomNumberImpl.randomValorAcao());
			empresaRepository.save(empresa);
		}
	}

}
