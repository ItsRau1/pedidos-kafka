package com.poc.fraude_service.outbound.kafka;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.fraude_service.core.gateway.PedidoGateway;
import com.poc.fraude_service.outbound.kafka.mapper.PedidoKafkaMapper;
import com.poc.fraude_service.outbound.kafka.utils.KafkaHandler;
import com.poc.kafka_schemas.avro.PedidoAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPedidoGateway implements PedidoGateway {

	private static final String LOG_PREFIX = "[KAFKA_PEDIDO_GATEWAY] ";

	private final KafkaTemplate<String, PedidoAvro> kafkaTemplate;

	private final PedidoKafkaMapper pedidoKafkaMapper;

	@Value("${kafka.topic.pedidos-validos}")
	private String topicPedidosValido;

	@Value("${kafka.topic.pedidos-fraudes}")
	private String topicPedidosFraude;

	@Override
	public void send(Pedido pedido, Boolean valido) {
		String topico = valido ? topicPedidosValido : topicPedidosFraude;
		log.info("{}Enviando pedido para topico kafka. [PEDIDO: {}] [TOPICO: {}]", LOG_PREFIX, pedido.getId(), topico);
		PedidoAvro pedidoAvro = pedidoKafkaMapper.toAvro(pedido);
		CompletableFuture.supplyAsync(() -> kafkaTemplate.send(topico, pedido.getUsuarioId().toString(), pedidoAvro))
			.handle(KafkaHandler.handle("%sFalha ao enviar mensagem".formatted(LOG_PREFIX)))
			.thenAccept(result -> log.info("{}Mensagem enviada com sucesso! [TOPICO: {}]", LOG_PREFIX, topico))
			.join();
	}

}
