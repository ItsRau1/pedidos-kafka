package com.poc.notificacao_service.inbound.mapper;

import com.poc.kafka_schemas.avro.PedidoAvro;
import com.poc.notificacao_service.core.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class, Instant.class, TimeZone.class })
public interface PedidoMapper {

	@Mapping(target = "timestamp",
			expression = "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(pedidoAvro.getTimestamp()), \n"
					+ "                                TimeZone.getDefault().toZoneId()))")
	Pedido toEntity(PedidoAvro pedidoAvro);

}
