package com.poc.pedidos_service.outbound.kafka.mappers;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.core.domain.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	@Mapping(target = "timestamp", expression = "java(LocalDateTime.now().toEpochMilli())")
	PedidoAvro toAvro(Pedido pedido);

}
