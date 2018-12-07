package com.bolsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bolsa.model.Empresa;
import com.bolsa.model.Monitoramento;
import com.bolsa.model.Negociacao;
import com.bolsa.model.enums.Transacao;
import com.bolsa.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;

	@Override
	public SimpleMailMessage constructMail(String assunto, String corpo, Negociacao negociacao) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(assunto);
		email.setText(corpo);
		email.setTo(negociacao.getConta().getEmail());
		email.setFrom("naoresponda@app.bolsa.com.br");
		return email;
	}

	@Override
	public SimpleMailMessage constructReportMail(Negociacao negociacao, Monitoramento monitoramento, Empresa empresa) {
		String assunto;
		String mensagem;
		if (negociacao.getTipoTransacao() == Transacao.VENDA) {
			assunto = "Venda de ações do(a) " + negociacao.getEmpresa();
			mensagem = "Preço de venda: " + monitoramento.getPrecoVenda() + ". Valor negociado: "
					+ empresa.getValorAcao();
		} else {
			assunto = "Compra de ações do(a) " + negociacao.getEmpresa();
			mensagem = "Preço de compra: " + monitoramento.getPrecoCompra() + ". Valor negociado: "
					+ empresa.getValorAcao();
		}
		return constructMail(assunto, mensagem, negociacao);
	}
	
	public void sendEmail(Negociacao negociacao, Monitoramento monitoramento, Empresa empresa) {
		sender.send(constructReportMail(negociacao, monitoramento, empresa));
	}

}
