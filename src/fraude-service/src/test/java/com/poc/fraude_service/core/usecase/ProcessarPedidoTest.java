package com.poc.fraude_service.core.usecase;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.fraude_service.core.gateway.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessarPedidoTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ProcessarPedido processarPedido;

    private Pedido pedidoValido;
    private Pedido pedidoFraudulento;

    @BeforeEach
    void setUp() {
        pedidoValido = Pedido.builder()
                .id(UUID.randomUUID().toString())
                .usuarioId(1L)
                .produtoId(100L)
                .quantidade(2)
                .timestamp(LocalDateTime.now().withNano(2))
                .build();
        pedidoFraudulento = Pedido.builder()
                .id(UUID.randomUUID().toString())
                .usuarioId(2L)
                .produtoId(200L)
                .quantidade(5)
                .timestamp(LocalDateTime.now().withNano(1))
                .build();
    }

    @Test
    void deveProcessarPedidoValidoComSucesso() {
        processarPedido.execute(pedidoValido);
        verify(pedidoGateway, times(1)).send(eq(pedidoValido), eq(true));
    }

    @Test
    void deveIdentificarPedidoComoFraude() {
        processarPedido.execute(pedidoFraudulento);
        verify(pedidoGateway, times(1)).send(eq(pedidoFraudulento), eq(false));
    }

    @Test
    void deveChamarGatewayApenasUmaVez() {
        processarPedido.execute(pedidoValido);
        verify(pedidoGateway, times(1)).send(any(Pedido.class), anyBoolean());
    }

}
