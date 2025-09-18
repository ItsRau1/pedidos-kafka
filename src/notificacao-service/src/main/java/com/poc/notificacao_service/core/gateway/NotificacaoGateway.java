package com.poc.notificacao_service.core.gateway;

import com.poc.notificacao_service.core.entity.Pedido;

public interface NotificacaoGateway {

	void send(Pedido pedido);

}
