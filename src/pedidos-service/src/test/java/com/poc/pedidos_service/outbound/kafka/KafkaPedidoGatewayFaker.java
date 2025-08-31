package com.poc.pedidos_service.outbound.kafka;

import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.core.gateway.PedidoGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class KafkaPedidoGatewayFaker implements PedidoGateway {

	@Override
	public void send(Pedido pedido) {
	}

}
