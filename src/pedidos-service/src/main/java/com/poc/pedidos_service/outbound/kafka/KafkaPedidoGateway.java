package com.poc.pedidos_service.outbound.kafka;

import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.core.gateway.PedidoGateway;
import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.outbound.kafka.mappers.PedidoMapper;
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
@Profile("!test")
@RequiredArgsConstructor
public class KafkaPedidoGateway implements PedidoGateway {

	private static final String LOG_PREFIX = "[KAFKA_PEDIDO_GATEWAY]";

	private final KafkaTemplate<String, PedidoAvro> kafkaTemplate;

    private final PedidoMapper pedidoMapper;

	@Value("${kafka.topic.pedidos}")
	private String topicPedidos;

	@Override
	public void send(Pedido pedido) {
		log.info("{}Enviando pedido para topico: [PEDIDO: {}] [TOPICO: {}]", LOG_PREFIX, pedido, topicPedidos);
		PedidoAvro pedidoAvro = pedidoMapper.toAvro(pedido);

		CompletableFuture
			.supplyAsync(() -> kafkaTemplate.send(topicPedidos, pedido.getUsuarioId().toString(), pedidoAvro))
			.thenAccept(result -> log.info("{}Mensagem enviada com sucesso! [TOPICO: {}]", LOG_PREFIX, topicPedidos))
			.handle(KafkaHandler.handle("%sFalha ao enviar mensagem".formatted(LOG_PREFIX)))
			.join();
		//@formatter:off

        // TODO: Ler artigo gerado pelo Gemini sobre exceções no completableFuture
		// TODO: Implementar Swagger

        //@formatter:on
	}

}
