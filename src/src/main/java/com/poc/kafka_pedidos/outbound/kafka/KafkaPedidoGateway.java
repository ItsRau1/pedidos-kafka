package com.poc.kafka_pedidos.outbound.kafka;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;
import com.poc.kafka_pedidos.core.gateway.PedidoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!test")
public class KafkaPedidoGateway implements PedidoGateway {

	private static final String LOG_PREFIX = "[KAFKA_PEDIDO_GATEWAY]";

	private static final String TOPICO_PEDIDO = "pedidos";

	@Override
	public void send(Pedido pedido) {
		log.info("{}Enviando pedido para topico: [PEDIDO: {}] [TOPICO: {}]", LOG_PREFIX, pedido, TOPICO_PEDIDO);
		// TODO: Implementar envio para o Kafka
	}

}
