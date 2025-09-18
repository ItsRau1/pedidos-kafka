package com.poc.fraude_service.inbound.listener;

import com.poc.fraude_service.inbound.facade.ProcessarPedidoFacade;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PedidoListenerTest {

	@Mock
	ProcessarPedidoFacade processarPedidoFacade;

	@InjectMocks
	PedidoListener pedidoListener;

	@BeforeEach
	void setUp() {
		doNothing().when(processarPedidoFacade).processarPedido(any(PedidoAvro.class));
	}

	@Test
	@DisplayName("Deve ser possível processar um pedido")
	void deveSerPossivelProcessarPedido() {
		pedidoListener.processarPedido(new PedidoAvro(), 0, 0);
		verify(processarPedidoFacade).processarPedido(any(PedidoAvro.class));
	}

}
