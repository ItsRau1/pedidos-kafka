package com.poc.pedidos_service.outbound.kafka;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.core.domain.entity.Pedido;
import com.poc.pedidos_service.outbound.kafka.mapper.PedidoKafkaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KafkaPedidoGatewayTest {

	@Mock
	private KafkaTemplate<String, PedidoAvro> kafkaTemplate;

	@Mock
	private PedidoKafkaMapper pedidoKafkaMapper;

	@InjectMocks
	private KafkaPedidoGateway kafkaPedidoGateway;

	@BeforeEach
	void setUp() {
		when(pedidoKafkaMapper.toAvro(any(Pedido.class))).thenReturn(new PedidoAvro());
	}

	@Test
	@DisplayName("Deve ser possível enviar um pedido")
	void deveSerPossivelEnviarPedidoValido() {
		Pedido pedido = Pedido.builder().usuarioId(1L).build();
		kafkaPedidoGateway.send(pedido);
		verify(kafkaTemplate).send(any(), any(String.class), any(PedidoAvro.class));
	}

	@Test
	@DisplayName("Deve disparar exception caso envio falhe")
	void deveDispararExceptionCasoEnvioFalhe() {
		when(kafkaTemplate.send(any(), any(String.class), any(PedidoAvro.class))).thenThrow(new RuntimeException());
		Pedido pedido = Pedido.builder().usuarioId(1L).build();
		assertThrows(CompletionException.class, () -> kafkaPedidoGateway.send(pedido));
	}

}
