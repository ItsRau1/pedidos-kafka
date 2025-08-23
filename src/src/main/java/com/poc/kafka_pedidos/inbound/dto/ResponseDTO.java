package com.poc.kafka_pedidos.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseDTO {

	private Integer status;

	private Boolean success;

	private String message;

	private final LocalDateTime timestamp = LocalDateTime.now();

}
