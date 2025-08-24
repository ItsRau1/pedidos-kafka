package com.poc.kafka_pedidos.outbound.kafka;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;
import com.poc.kafka_pedidos.core.gateway.PedidoGateway;
import com.poc.kafka_pedidos.outbound.kafka.avro.PedidoAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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
			.thenAccept(result -> log.info("Mensagem enviada com sucesso! [TOPICO: {}]", topicPedidos))
			.handle((res, ex) -> {
				if (ex == null)
					return null;
				log.error("Falha ao enviar mensagem", ex);
				throw new CompletionException(ex);
			})
			.join();
		//@formatter:off

        // TODO: Melhorar código do .handle
        // TODO: Verificar possível integração entre mapStruct e Avro
        // TODO: Perguntar para um IA sobre quais possíveis erros podem ocorrer em uma conexão com o KAFKA (Passando todo o contexto de ser uma api e usar ExceptionHandler)
        // TODO: Ler artigo gerado pelo Gemini sobre exceções no completableFuture
        // TODO: Investigar pq o faker não está sendo chamado no momento dos testes e sim o bean KafkaPedidoGateway

        //@formatter:on
	}

}
