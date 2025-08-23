package com.poc.kafka_pedidos.inbound.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorDTO {

	private final String description;

	public ErrorDTO(String description) {
		this.description = description;
	}

}
