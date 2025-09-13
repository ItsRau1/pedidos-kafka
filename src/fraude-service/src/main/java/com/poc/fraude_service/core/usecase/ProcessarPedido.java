package com.poc.fraude_service.core.usecase;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.fraude_service.core.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessarPedido {

	private final String LOG_PREFIX = "[PROCESSAR-PEDIDO] - ";

	private final PedidoGateway pedidoGateway;

	public void execute(Pedido pedido) {
		log.info("{}Iniciando processamento de pedido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
		var isFraude = verificarFraude(pedido);
		if (isFraude) {
			log.info("{}Pedido identificado como fraude. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
			pedidoGateway.send(pedido, false);
			return;
		}
		log.info("{}Pedido valido. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
		pedidoGateway.send(pedido, true);
	}

	private Boolean verificarFraude(Pedido pedido) {
		log.info("{}Verificando se pedido é fraude. [PEDIDO: {}]", LOG_PREFIX, pedido.getId());
		return !isPar(pedido.getTimestamp().getSecond());
	}

	private Boolean isPar(int value) {
		return value % 2 == 0;
	}

}
