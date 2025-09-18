package com.poc.pedidos_service.outbound.kafka;

import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.core.gateway.PedidoGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionException;

@Component
@Profile("integration")
public class KafkaPedidoGatewayFaker implements PedidoGateway {

	@Override
	public void send(Pedido pedido) {
		if (pedido.getUsuarioId().equals(98789L)) {
			throw new CompletionException(new RuntimeException("Erro ao enviar pedido para fila"));
		}
	}

}
