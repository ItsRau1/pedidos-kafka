package com.poc.pedidos_service.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(description = "Id do usuario", example = "1")
	@NotNull(message = "usuarioId deve ser informado.")
	@Min(value = 1, message = "usuarioId deve ser maior que 0.")
	private Long usuarioId;

	@Schema(description = "Id do produto", example = "1")
	@NotNull(message = "produtoId deve ser informado.")
	@Min(value = 1, message = "produtoId deve ser maior que 0.")
	private Long produtoId;

	@Schema(description = "Quantidade do produto", example = "1")
	@NotNull(message = "quantidade deve ser informada.")
	@Min(value = 1, message = "quantidade deve ser maior que 0.")
	private Integer quantidade;

}
