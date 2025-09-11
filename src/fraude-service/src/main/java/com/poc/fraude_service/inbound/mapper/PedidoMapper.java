package com.poc.fraude_service.inbound.mapper;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	Pedido toEntity(PedidoAvro pedidoAvro);

}
