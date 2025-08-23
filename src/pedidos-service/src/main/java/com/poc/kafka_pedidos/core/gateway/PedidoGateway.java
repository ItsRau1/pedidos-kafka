package com.poc.kafka_pedidos.core.gateway;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;

public interface PedidoGateway {

	void send(Pedido pedido);

}
