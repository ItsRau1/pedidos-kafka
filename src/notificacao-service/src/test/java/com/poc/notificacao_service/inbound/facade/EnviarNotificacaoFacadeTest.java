package com.poc.notificacao_service.inbound.facade;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.core.entity.Pedido;
import com.poc.notificacao_service.core.usecase.EnviarNotificacao;
import com.poc.notificacao_service.inbound.mapper.PedidoMapper;
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
class EnviarNotificacaoFacadeTest {

	@Mock
	EnviarNotificacao enviarNotificacao;

	@Mock
	PedidoMapper pedidoMapper;

	@InjectMocks
	EnviarNotificacaoFacade enviarNotificacaoFacade;

	@BeforeEach
	void setUp() {
		doNothing().when(enviarNotificacao).execute(any(Pedido.class));
		when(pedidoMapper.toEntity(any(PedidoAvro.class))).thenReturn(new Pedido());
	}

	@Test
	@DisplayName("Deve ser possível enviar notificacao de um pedido")
	void deveSerPossivelEnviarNotificacaoPedido() {
		enviarNotificacaoFacade.enviarNotificacao(new PedidoAvro());
		verify(enviarNotificacao).execute(any(Pedido.class));
	}

}
