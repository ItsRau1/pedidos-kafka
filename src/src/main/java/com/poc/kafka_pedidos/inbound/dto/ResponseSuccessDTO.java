package com.poc.kafka_pedidos.inbound.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ResponseSuccessDTO<C> extends ResponseDTO {

	private final C content;

	private final Map<String, Object> parameters;

	private static final String DEFAULT_MESSAGE = "Operação realizada com sucesso.";

	public ResponseSuccessDTO(Integer status, C content, Map<String, Object> parameters) {
		super(status, Boolean.TRUE, DEFAULT_MESSAGE);
		this.content = content;
		this.parameters = parameters;
	}

}
