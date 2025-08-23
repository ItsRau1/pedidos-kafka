package com.poc.kafka_pedidos.inbound.facade;

import com.poc.kafka_pedidos.core.domain.entity.Pedido;
import com.poc.kafka_pedidos.core.usecase.ReceberPedido;
import com.poc.kafka_pedidos.inbound.dto.ReceberPedidoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceberPedidoFacade {

	private final ReceberPedido receberPedido;

	private static final String LOG_PREFIX = "[RECEBER-PEDIDO-FACADE] ";

	public void receberPedido(ReceberPedidoDTO pedidoDTO) {
		log.info("{}Iniciando recebimento de pedido: [PEDIDO: {}]", LOG_PREFIX, pedidoDTO);
		Pedido pedido = Pedido.builder()
			.usuarioId(pedidoDTO.getUsuarioId())
			.produtoId(pedidoDTO.getProdutoId())
			.quantidade(pedidoDTO.getQuantidade())
			.build();
		receberPedido.execute(pedido);
	}

}
