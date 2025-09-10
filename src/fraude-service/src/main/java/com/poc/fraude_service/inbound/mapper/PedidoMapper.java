package com.poc.fraude_service.inbound.mapper;

import com.poc.fraude_service.core.domain.entity.Pedido;
import com.poc.kafka_schemas.avro.PedidoAvro;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toEntity(PedidoAvro pedidoAvro);

//    default LocalDateTime convertTimestamp(long value) {
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault());
//    }
}
