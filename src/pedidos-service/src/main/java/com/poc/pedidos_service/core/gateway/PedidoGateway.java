package com.poc.pedidos_service.core.gateway;

import com.poc.pedidos_service.core.domain.entity.Pedido;

public interface PedidoGateway {

	void send(Pedido pedido);

}
