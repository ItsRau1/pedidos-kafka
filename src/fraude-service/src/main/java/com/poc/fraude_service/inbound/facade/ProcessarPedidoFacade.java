package com.poc.fraude_service.inbound.facade;

import com.poc.fraude_service.core.usecase.ProcessarPedido;
import com.poc.fraude_service.inbound.mapper.PedidoMapper;
import com.poc.kafka_schemas.avro.PedidoAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessarPedidoFacade {

	private final String LOG_PREFIX = "[PROCESSAR-PEDIDO-FACADE] - ";

	private final PedidoMapper pedidoMapper;

	private final ProcessarPedido processarPedido;

	public void processarPedido(PedidoAvro pedidoAvro) {
		log.info("{}Inciando processamento de pedido. [PEDIDO: {}]", LOG_PREFIX, pedidoAvro.getId());
		processarPedido.execute(pedidoMapper.toEntity(pedidoAvro));
	}

}
