package com.poc.pedidos_service.outbound.kafka;

import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.core.gateway.PedidoGateway;
import com.poc.kafka_schemas.avro.PedidoAvro;
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

	@Value("${kafka.topic.pedidos}")
	private String topicPedidos;

	@Override
	public void send(Pedido pedido) {
		log.info("{}Enviando pedido para topico: [PEDIDO: {}] [TOPICO: {}]", LOG_PREFIX, pedido, topicPedidos);
		PedidoAvro pedidoAvro = PedidoAvro.newBuilder()
			.setId(pedido.getId())
			.setUsuarioId(pedido.getUsuarioId())
			.setProdutoId(pedido.getProdutoId())
			.setQuantidade(pedido.getQuantidade())
			.build();

		CompletableFuture
			.supplyAsync(() -> kafkaTemplate.send(topicPedidos, pedido.getUsuarioId().toString(), pedidoAvro))
			.thenAccept(result -> log.info("{}Mensagem enviada com sucesso! [TOPICO: {}]", LOG_PREFIX, topicPedidos))
			.handle(KafkaHandler.handle("%sFalha ao enviar mensagem".formatted(LOG_PREFIX)))
			.join();
		//@formatter:off

        // TODO: Verificar possível integração entre mapStruct e Avro
        // TODO: Perguntar para um IA sobre quais possíveis erros podem ocorrer em uma conexão com o KAFKA (Passando todo o contexto de ser uma api e usar ExceptionHandler)
        // TODO: Ler artigo gerado pelo Gemini sobre exceções no completableFuture
		// TODO: Implementar Swagger

        //@formatter:on
	}

}
