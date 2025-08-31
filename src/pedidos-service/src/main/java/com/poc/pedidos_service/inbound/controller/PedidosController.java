package com.poc.pedidos_service.inbound.controller;

import com.poc.pedidos_service.inbound.dto.ReceberPedidoDTO;
import com.poc.pedidos_service.inbound.dto.ResponseDTO;
import com.poc.pedidos_service.inbound.dto.ResponseSuccessDTO;
import com.poc.pedidos_service.inbound.facade.ReceberPedidoFacade;
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

	@PostMapping("/novo-pedido")
	public ResponseEntity<ResponseDTO> receberPedido(@RequestBody @Valid ReceberPedidoDTO pedidoDTO) {
		receberPedidoFacade.receberPedido(pedidoDTO);
		int status = 200;
		Map<String, Object> parameters = Map.of("payload", pedidoDTO);
		return ResponseEntity.status(status).body(new ResponseSuccessDTO<>(status, null, parameters));
	}

}
