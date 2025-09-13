package com.poc.fraude_service.inbound.listener;

import com.poc.fraude_service.inbound.facade.ProcessarPedidoFacade;
import com.poc.kafka_schemas.avro.PedidoAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Profile("!test")
@RequiredArgsConstructor
public class PedidosListener {

	private final String LOG_PREFIX = "[PEDIDOS-LISTENER] - ";

	final ProcessarPedidoFacade processarPedidoFacade;

	@KafkaListener(topics = "${kafka.topic.pedidos}", groupId = "${spring.kafka.consumer.group-id}")
	public void processarPedido(@Payload PedidoAvro pedido, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
			@Header(KafkaHeaders.OFFSET) long offset) {
		log.info("{}Iniciando processamento de pedido. [PEDIDO: {}] [PARTITION: {}] [OFFSET: {}]", LOG_PREFIX,
				pedido.getId(), partition, offset);
		processarPedidoFacade.processarPedido(pedido);
	}

}
