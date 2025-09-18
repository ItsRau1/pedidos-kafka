package com.poc.notificacao_service.core.usecase;

import com.poc.notificacao_service.core.entity.Pedido;
import com.poc.notificacao_service.core.gateway.NotificacaoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EnviarNotificacaoTest {

	@Mock
	private NotificacaoGateway notificacaoGateway;

	@InjectMocks
	private EnviarNotificacao enviarNotificacao;

	private Pedido pedido;

	@BeforeEach
	void setUp() {
		pedido = Pedido.builder()
			.id(UUID.randomUUID().toString())
			.usuarioId(1L)
			.produtoId(100L)
			.quantidade(2)
			.timestamp(LocalDateTime.now())
			.build();
	}

	@Test
	void deveEnviarNotificacaoComSucesso() {
		enviarNotificacao.execute(pedido);
		verify(notificacaoGateway, times(1)).send(eq(pedido));
	}

}
