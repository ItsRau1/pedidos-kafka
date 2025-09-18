package com.poc.notificacao_service.outbound;

import com.poc.notificacao_service.core.entity.Pedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificacaoGatewayImplTest {

	@InjectMocks
	NotificacaoGatewayImpl notificacaoGateway;

	@Test
	void deveEnviarNotificacao() {
		notificacaoGateway.send(new Pedido());
	}

}
