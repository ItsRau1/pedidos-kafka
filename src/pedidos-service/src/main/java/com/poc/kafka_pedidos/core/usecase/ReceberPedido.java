package com.poc.kafka_pedidos.core.usecase;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;
import com.poc.kafka_pedidos.core.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceberPedido {

	private final PedidoGateway pedidoGateway;

	private static final String LOG_PREFIX = "[RECEBER-PEDIDO] ";

	public void execute(Pedido pedido) {
		log.info("{}Recebendo pedido: [PEDIDO: {}]", LOG_PREFIX, pedido);
		pedidoGateway.send(pedido);
	}

}
