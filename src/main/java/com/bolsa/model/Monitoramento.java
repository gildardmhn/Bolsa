package com.bolsa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bolsa.views.ContaView.MonitoramentoView;
import com.bolsa.views.ContaView.Contato;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Monitoramento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String empresa;
	private Double precoCompra;
	private Double precoVenda;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Conta conta;

	@JsonView({ Contato.class, MonitoramentoView.class })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonView({ Contato.class, MonitoramentoView.class })
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@JsonView({ Contato.class, MonitoramentoView.class })
	public Double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	@JsonView({ Contato.class, MonitoramentoView.class })
	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

//	@JsonView(MonitoramentoView.class)
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
