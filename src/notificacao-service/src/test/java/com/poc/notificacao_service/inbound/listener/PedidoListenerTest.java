package com.poc.notificacao_service.inbound.listener;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.inbound.facade.EnviarNotificacaoFacade;
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
	EnviarNotificacaoFacade enviarNotificacaoFacade;

	@InjectMocks
	PedidoListener pedidoListener;

	@BeforeEach
	void setUp() {
		doNothing().when(enviarNotificacaoFacade).enviarNotificacao(any(PedidoAvro.class));
	}

	@Test
	@DisplayName("Deve ser possível processar um pedido")
	void deveSerPossivelProcessarPedido() {
		pedidoListener.enviarNotificacao(new PedidoAvro(), 0, 0);
		verify(enviarNotificacaoFacade).enviarNotificacao(any(PedidoAvro.class));
	}

}
