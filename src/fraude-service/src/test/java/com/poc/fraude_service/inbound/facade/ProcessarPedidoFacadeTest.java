package com.poc.fraude_service.inbound.facade;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.fraude_service.core.usecase.ProcessarPedido;
import com.poc.fraude_service.inbound.mapper.PedidoMapper;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessarPedidoFacadeTest {

	@Mock
	ProcessarPedido processarPedido;

	@Mock
	PedidoMapper pedidoMapper;

	@InjectMocks
	ProcessarPedidoFacade processarPedidoFacade;

	@BeforeEach
	void setUp() {
		doNothing().when(processarPedido).execute(any(Pedido.class));
		when(pedidoMapper.toEntity(any(PedidoAvro.class))).thenReturn(new Pedido());
	}

	@Test
	@DisplayName("Deve ser possível processar um pedido")
	void deveSerPossivelProcessarPedido() {
		processarPedidoFacade.processarPedido(new PedidoAvro());
		verify(processarPedido).execute(any(Pedido.class));
	}

}
