package com.poc.fraude_service.outbound.kafka;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.fraude_service.core.gateway.PedidoGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class KafkaPedidoGatewayFaker implements PedidoGateway {

    @Override
    public void send(Pedido pedido, Boolean valido) {
    }

}
