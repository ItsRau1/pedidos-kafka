package com.poc.kafka_pedidos.inbound.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceberPedidoDTO {

	@NotNull(message = "usuarioId deve ser informado.")
	@Min(value = 1, message = "usuarioId deve ser maior que 0.")
	private Long usuarioId;

	@NotNull(message = "produtoId deve ser informado.")
	@Min(value = 1, message = "produtoId deve ser maior que 0.")
	private Long produtoId;

	@NotNull(message = "quantidade deve ser informada.")
	@Min(value = 1, message = "quantidade deve ser maior que 0.")
	private Integer quantidade;

}
