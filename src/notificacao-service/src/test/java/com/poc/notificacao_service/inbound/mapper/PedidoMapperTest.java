package com.poc.notificacao_service.inbound.mapper;

import com.poc.kafka_schemas.avro.PedidoAvro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PedidoMapperTest {

	private final PedidoMapper mapper = Mappers.getMapper(PedidoMapper.class);

	@Test
	@DisplayName("Deve ser possível mapear um pedido")
	void deveSerPossivelMapearPedido() {
		PedidoAvro pedidoAvro = new PedidoAvro();
		pedidoAvro.setId("1");
		pedidoAvro.setUsuarioId(2);
		pedidoAvro.setProdutoId(3);
		pedidoAvro.setQuantidade(4);
		pedidoAvro.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		com.poc.notificacao_service.core.entity.Pedido pedido = mapper.toEntity(pedidoAvro);
		assertEquals(pedidoAvro.getId(), pedido.getId());
		assertEquals(pedidoAvro.getUsuarioId(), pedido.getUsuarioId());
		assertEquals(pedidoAvro.getProdutoId(), pedido.getProdutoId());
		assertEquals(pedidoAvro.getQuantidade(), pedido.getQuantidade());
		assertEquals(pedidoAvro.getTimestamp(),
				pedido.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	}

	@Test
	@DisplayName("Deve tratar parâmetro null")
	void deveTratarParametroNull() {
		var value = mapper.toEntity(null);
		assertNull(value);
	}

}
