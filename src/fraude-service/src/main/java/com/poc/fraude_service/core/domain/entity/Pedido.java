package com.poc.fraude_service.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private CharSequence id;

    private Long usuarioId;

    private Long produtoId;

    private int quantidade;

}
