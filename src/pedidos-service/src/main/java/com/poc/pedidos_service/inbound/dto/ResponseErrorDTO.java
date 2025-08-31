package com.poc.pedidos_service.inbound.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseErrorDTO extends ResponseDTO {

	private final List<ErrorDTO> errors;

	public ResponseErrorDTO(Integer status, String message, List<ErrorDTO> errors) {
		super(status, Boolean.FALSE, message);
		this.errors = errors;
	}

}
