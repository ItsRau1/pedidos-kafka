package com.poc.notificacao_service.outbound;

import com.poc.notificacao_service.core.entity.Pedido;
import com.poc.notificacao_service.core.gateway.NotificacaoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacaoGatewayImpl implements NotificacaoGateway {

	private static final String LOG_PREFIX = "[NOTIFICACAO-GATEWAY] - ";

	@Override
	public void send(Pedido pedido) {
		log.info("{}Notificacao Enviada. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
	}

}
