package com.poc.pedidos_service.inbound.controller;

import com.poc.pedidos_service.inbound.dto.ErrorDTO;
import com.poc.pedidos_service.inbound.dto.ReceberPedidoDTO;
import com.poc.pedidos_service.inbound.dto.ResponseErrorDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidosControllerTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	private static final String BASE_PATH = "/v1/kafka-pedidos";

	@Test
	@DisplayName("Deve ser possível fazer um novo pedido")
	void deveSerPossivelFazerNovoPedido() {
		ReceberPedidoDTO receberPedidoDTO = ReceberPedidoDTO.builder()
			.usuarioId(1L)
			.produtoId(1L)
			.quantidade(1)
			.build();
		RestAssured.given()
			.contentType("application/json")
			.when()
			.body(receberPedidoDTO)
			.post(BASE_PATH.concat("/novo-pedido"))
			.then()
			.statusCode(200);
	}

	@ParameterizedTest
	@MethodSource("usuarioIdInvalido")
	@DisplayName("Não deve ser possível fazer um novo pedido enviando usuarioId invalido")
	void naoDeveSerPossivelFazerNovoPedidoEnviandoUsuarioIdInvalido(String expectedMessage, Long usuarioId) {
		ReceberPedidoDTO receberPedidoDTO = ReceberPedidoDTO.builder()
			.usuarioId(usuarioId)
			.produtoId(1L)
			.quantidade(1)
			.build();
		ResponseErrorDTO response = RestAssured.given()
			.contentType("application/json")
			.when()
			.body(receberPedidoDTO)
			.post(BASE_PATH.concat("/novo-pedido"))
			.then()
			.statusCode(400)
			.extract()
			.body()
			.as(ResponseErrorDTO.class);
		ErrorDTO errorDTO = response.getErrors().getFirst();
		Assertions.assertEquals(expectedMessage, errorDTO.getDescription());
	}

	@ParameterizedTest
	@MethodSource("produtoIdInvalido")
	@DisplayName("Não deve ser possível fazer um novo pedido enviando produtoId invalido")
	void naoDeveSerPossivelFazerNovoPedidoEnviandoProdutoIdInvalido(String expectedMessage, Long produtoId) {
		ReceberPedidoDTO receberPedidoDTO = ReceberPedidoDTO.builder()
			.usuarioId(1L)
			.produtoId(produtoId)
			.quantidade(1)
			.build();
		ResponseErrorDTO response = RestAssured.given()
			.contentType("application/json")
			.when()
			.body(receberPedidoDTO)
			.post(BASE_PATH.concat("/novo-pedido"))
			.then()
			.statusCode(400)
			.extract()
			.body()
			.as(ResponseErrorDTO.class);
		ErrorDTO errorDTO = response.getErrors().getFirst();
		Assertions.assertEquals(expectedMessage, errorDTO.getDescription());
	}

	@ParameterizedTest
	@MethodSource("quantidadeInvalida")
	@DisplayName("Não deve ser possível fazer um novo pedido enviando quantidade invalida")
	void naoDeveSerPossivelFazerNovoPedidoEnviandoQuantidadeInvalida(String expectedMessage, Integer quantidade) {
		ReceberPedidoDTO receberPedidoDTO = ReceberPedidoDTO.builder()
			.usuarioId(1L)
			.produtoId(1L)
			.quantidade(quantidade)
			.build();
		ResponseErrorDTO response = RestAssured.given()
			.contentType("application/json")
			.when()
			.body(receberPedidoDTO)
			.post(BASE_PATH.concat("/novo-pedido"))
			.then()
			.statusCode(400)
			.extract()
			.body()
			.as(ResponseErrorDTO.class);
		ErrorDTO errorDTO = response.getErrors().getFirst();
		Assertions.assertEquals(expectedMessage, errorDTO.getDescription());
	}

	private static Stream<Arguments> usuarioIdInvalido() {
		return Stream.of(Arguments.of("usuarioId deve ser informado.", null),
				Arguments.of("usuarioId deve ser maior que 0.", 0L),
				Arguments.of("usuarioId deve ser maior que 0.", -2L));
	}

	private static Stream<Arguments> quantidadeInvalida() {
		return Stream.of(Arguments.of("quantidade deve ser informada.", null),
				Arguments.of("quantidade deve ser maior que 0.", 0),
				Arguments.of("quantidade deve ser maior que 0.", -2));
	}

	private static Stream<Arguments> produtoIdInvalido() {
		return Stream.of(Arguments.of("produtoId deve ser informado.", null),
				Arguments.of("produtoId deve ser maior que 0.", 0L),
				Arguments.of("produtoId deve ser maior que 0.", -2L));
	}

}
