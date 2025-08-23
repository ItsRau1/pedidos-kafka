package com.poc.kafka_pedidos.core.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pedido {

	private Long usuarioId;

	private Long produtoId;

	private Integer quantidade;

}
