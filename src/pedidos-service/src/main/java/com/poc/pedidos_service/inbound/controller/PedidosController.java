package com.poc.pedidos_service.inbound.controller;

import com.poc.pedidos_service.inbound.dto.ReceberPedidoDTO;
import com.poc.pedidos_service.inbound.dto.ResponseDTO;
import com.poc.pedidos_service.inbound.dto.ResponseErrorDTO;
import com.poc.pedidos_service.inbound.dto.ResponseSuccessDTO;
import com.poc.pedidos_service.inbound.facade.ReceberPedidoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/kafka-pedidos")
@RequiredArgsConstructor
public class PedidosController {

	private final ReceberPedidoFacade receberPedidoFacade;

	@Operation(summary = "Recebe um novo pedido",
			description = "Endpoint para recebimento de novos pedidos. O pedido será processado e encaminhado para o sistema de pagamento.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Pedido recebido com sucesso",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ResponseSuccessDTO.class))),
					@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ResponseErrorDTO.class))),
					@ApiResponse(responseCode = "500", description = "Erro interno do servidor",
							content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ResponseErrorDTO.class))) })
	@PostMapping("/novo-pedido")
	public ResponseEntity<ResponseDTO> receberPedido(@RequestBody @Valid ReceberPedidoDTO pedidoDTO) {
		receberPedidoFacade.receberPedido(pedidoDTO);
		int status = 200;
		Map<String, Object> parameters = Map.of("payload", pedidoDTO);
		return ResponseEntity.status(status).body(new ResponseSuccessDTO<>(status, null, parameters));
	}

}
