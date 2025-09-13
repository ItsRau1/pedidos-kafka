package com.poc.fraude_service.outbound.kafka.mapper;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class, ZoneId.class })
public interface PedidoKafkaMapper {

	@Mapping(target = "timestamp",
			expression = "java(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())")
	PedidoAvro toAvro(Pedido pedido);

}
