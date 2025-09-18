package com.poc.notificacao_service.inbound.listener;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.inbound.facade.EnviarNotificacaoFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Profile("!test")
@RequiredArgsConstructor
public class PedidoListener {

	private final String LOG_PREFIX = "[PEDIDOS-LISTENER] - ";

	final EnviarNotificacaoFacade enviarNotificacaoFacade;

	@KafkaListener(topics = "${kafka.topic.pedidos-validos}", groupId = "${spring.kafka.consumer.group-id}")
	public void enviarNotificacao(@Payload PedidoAvro pedido, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
			@Header(KafkaHeaders.OFFSET) long offset) {
		log.info("{}Iniciando envio de notificacao para pedido. [PEDIDO: {}] [PARTITION: {}] [OFFSET: {}]", LOG_PREFIX,
				pedido.getId(), partition, offset);
		enviarNotificacaoFacade.enviarNotificacao(pedido);
	}

}
