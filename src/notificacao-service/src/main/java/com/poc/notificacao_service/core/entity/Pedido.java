package com.poc.notificacao_service.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

	private CharSequence id;

	private Long usuarioId;

	private Long produtoId;

	private int quantidade;

	private LocalDateTime timestamp;

}
