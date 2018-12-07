package com.bolsa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bolsa.views.ContaView.Contato;
import com.bolsa.views.ContaView.MonitoramentoView;
import com.bolsa.views.ContaView.TodasAsContas;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;
	private float saldo;
	private float numeroAcoes = 0;

	@OneToMany(mappedBy = "conta")
	private List<Monitoramento> monitoramentos;

	@OneToMany(mappedBy = "conta")
	private List<Negociacao> negociacoes;

	@JsonView({ Contato.class, MonitoramentoView.class, TodasAsContas.class })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonView({ Contato.class, MonitoramentoView.class, TodasAsContas.class })
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonView({ Contato.class, MonitoramentoView.class, TodasAsContas.class })
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	@JsonView({ Contato.class, MonitoramentoView.class, TodasAsContas.class })
	public float getNumeroAcoes() {
		return numeroAcoes;
	}

	public void setNumeroAcoes(float numeroAcoes) {
		this.numeroAcoes = numeroAcoes;
	}

	@JsonView(Contato.class)
	public List<Monitoramento> getMonitoramentos() {
		return monitoramentos;
	}

	public void setMonitoramentos(List<Monitoramento> monitoramentos) {
		this.monitoramentos = monitoramentos;
	}

	@JsonView(Contato.class)
	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
	}

}
