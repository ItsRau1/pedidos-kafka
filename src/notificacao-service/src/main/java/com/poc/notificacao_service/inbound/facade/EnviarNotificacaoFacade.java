package com.poc.notificacao_service.inbound.facade;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.core.usecase.EnviarNotificacao;
import com.poc.notificacao_service.inbound.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnviarNotificacaoFacade {

	private static final String LOG_PREFIX = "[ENVIAR-NOTIFICACAO] - ";

	private final PedidoMapper mapper;

	private final EnviarNotificacao enviarNotificacao;

	public void enviarNotificacao(PedidoAvro pedido) {
		log.info("{}Iniciando envio de notificacao para pedido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
		enviarNotificacao.execute(mapper.toEntity(pedido));
	}

}
