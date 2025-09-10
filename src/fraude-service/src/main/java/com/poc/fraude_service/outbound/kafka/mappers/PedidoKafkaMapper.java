package com.poc.fraude_service.outbound.kafka.mappers;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoKafkaMapper {

    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now().toEpochMilli())")
	PedidoAvro toAvro(Pedido pedido);

}
