package com.poc.fraude_service.inbound.mapper;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class, Instant.class, TimeZone.class })
public interface PedidoMapper {

	@Mapping(target = "timestamp",
			expression = "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(pedidoAvro.getTimestamp()), \n"
					+ "                                TimeZone.getDefault().toZoneId()))")
	Pedido toEntity(PedidoAvro pedidoAvro);

}
