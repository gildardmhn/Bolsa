package com.bolsa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String email;
	private Double saldo;
	private Double numeroAcoes;
	
	@OneToMany(mappedBy = "conta")
	private List<Monitoramento> monitoramento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getNumeroAcoes() {
		return numeroAcoes;
	}
	public void setNumeroAcoes(Double numeroAcoes) {
		this.numeroAcoes = numeroAcoes;
	}
	
}
