package com.bolsa.service;

import org.springframework.mail.SimpleMailMessage;

import com.bolsa.model.Empresa;
import com.bolsa.model.Monitoramento;
import com.bolsa.model.Negociacao;

public interface EmailService {

	SimpleMailMessage constructReportMail(Negociacao negociacao, Monitoramento monitoramento, Empresa empresa);

	SimpleMailMessage constructMail(String assunto, String corpo, Negociacao negociacao);

}
