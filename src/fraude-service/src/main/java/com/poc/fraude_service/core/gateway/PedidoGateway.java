package com.poc.fraude_service.core.gateway;

import com.poc.fraude_service.core.domain.entity.Pedido;

public interface PedidoGateway {

    void send(Pedido pedido, Boolean valido);

}
