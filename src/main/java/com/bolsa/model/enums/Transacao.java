package com.bolsa.model.enums;

public enum Transacao {

	COMPRA("Compra"), VENDA("Venda");

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	Transacao(String valor) {
		descricao = valor;
	}
}
