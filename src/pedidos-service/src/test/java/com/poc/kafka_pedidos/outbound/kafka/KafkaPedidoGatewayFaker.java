package com.poc.kafka_pedidos.outbound.kafka;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;
import com.poc.kafka_pedidos.core.gateway.PedidoGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class KafkaPedidoGatewayFaker implements PedidoGateway {

	@Override
	public void send(Pedido pedido) {
	}

}
