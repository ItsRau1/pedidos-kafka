package com.poc.pedidos_service.outbound.kafka.mappers;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.pedidos_service.core.domain.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class, ZoneId.class })
public interface PedidoMapper {

	@Mapping(target = "timestamp",
			expression = "java(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())")
	PedidoAvro toAvro(Pedido pedido);

}
