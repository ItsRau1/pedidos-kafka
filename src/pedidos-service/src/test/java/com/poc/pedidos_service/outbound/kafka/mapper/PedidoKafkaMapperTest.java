package com.poc.pedidos_service.outbound.kafka.mapper;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.core.domain.entity.Pedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class PedidoKafkaMapperTest {

	private final PedidoKafkaMapper mapper = Mappers.getMapper(PedidoKafkaMapper.class);

	@Test
	@DisplayName("Deve ser possível mapear um pedido")
	void deveSerPossivelMapearPedido() {
		Pedido pedido1 = Pedido.builder().id("1").usuarioId(2L).produtoId(3L).quantidade(4).build();
		PedidoAvro pedidoAvro = mapper.toAvro(pedido1);
		assertEquals(pedido1.getId(), pedidoAvro.getId());
		assertEquals(pedido1.getUsuarioId(), pedidoAvro.getUsuarioId());
		assertEquals(pedido1.getProdutoId(), pedidoAvro.getProdutoId());
		assertEquals(pedido1.getQuantidade(), pedidoAvro.getQuantidade());
		assertNotNull(LocalDateTime.ofInstant(Instant.ofEpochMilli(pedidoAvro.getTimestamp()),
				TimeZone.getDefault().toZoneId()));
	}

	@Test
	@DisplayName("Deve tratar parâmetro null")
	void deveTratarParametroNull() {
		var value = mapper.toAvro(null);
		assertNull(value);
	}

}
