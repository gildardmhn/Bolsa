package com.bolsa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bolsa.model.enums.Transacao;
import com.bolsa.views.ContaView.ViewConta;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Negociacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Transacao tipoTransacao;

	private String empresa;
	private float valor;
	private float quantidadeAcoes;
	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Conta conta;

	@JsonView(ViewConta.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonView(ViewConta.class)
	public Transacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(Transacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	@JsonView(ViewConta.class)
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@JsonView(ViewConta.class)
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@JsonView(ViewConta.class)
	public float getQuantidadeAcoes() {
		return quantidadeAcoes;
	}

	public void setQuantidadeAcoes(float quantidade) {
		this.quantidadeAcoes = quantidade;
	}

	@JsonView(ViewConta.class)
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
