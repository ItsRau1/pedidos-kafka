package com.poc.pedidos_service.outbound.kafka;

import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.core.gateway.PedidoGateway;
import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.outbound.kafka.mapper.PedidoKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.poc.pedidos_service.outbound.kafka.utils.KafkaHandler;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@Profile("!integration")
@RequiredArgsConstructor
public class KafkaPedidoGateway implements PedidoGateway {

	private static final String LOG_PREFIX = "[KAFKA_PEDIDO_GATEWAY]";

	private final KafkaTemplate<String, PedidoAvro> kafkaTemplate;

	private final PedidoKafkaMapper pedidoKafkaMapper;

	@Value("${kafka.topic.pedidos}")
	private String topicPedidos;

	@Override
	public void send(Pedido pedido) {
		log.info("{}Enviando pedido para topico: [PEDIDO: {}] [TOPICO: {}]", LOG_PREFIX, pedido, topicPedidos);
		PedidoAvro pedidoAvro = pedidoKafkaMapper.toAvro(pedido);

		CompletableFuture
			.supplyAsync(() -> kafkaTemplate.send(topicPedidos, pedido.getUsuarioId().toString(), pedidoAvro))
			.handle(KafkaHandler.handle("%sFalha ao enviar mensagem".formatted(LOG_PREFIX)))
			.thenAccept(result -> log.info("{}Mensagem enviada com sucesso! [TOPICO: {}]", LOG_PREFIX, topicPedidos))
			.join();
	}

}
