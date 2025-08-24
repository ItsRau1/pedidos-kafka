package com.poc.kafka_pedidos.core.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Pedido {

	private String id;

	private Long usuarioId;

	private Long produtoId;

	private Integer quantidade;

	public void gerarId() {
		this.id = UUID.randomUUID().toString();
	}

}
