package com.poc.notificacao_service.core.usecase;

import com.poc.notificacao_service.core.entity.Pedido;
import com.poc.notificacao_service.core.gateway.NotificacaoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnviarNotificacao {

	private final static String LOG_PREFIX = "[ENVIAR-NOTIFICACAO] - ";

	private final NotificacaoGateway notificacaoGateway;

	public void execute(Pedido pedido) {
		log.info("{}Enviando notificacao para o pedido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
		notificacaoGateway.send(pedido);
		log.info("{}Notificacao enviada para o pedido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
	}

}
