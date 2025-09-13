package com.poc.fraude_service.outbound.kafka.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KafkaHandlerTest {

	@Test
	@DisplayName("Nao deve lancar exception caso ela nao seja enviada")
	void naoDeveLancarExceptionCasoNaoSejaEnviada() {
		String result = "result";
		var value = KafkaHandler.handle("Erro").apply(result, null);
		assertEquals(result, value);
	}

	@Test
	@DisplayName("Deve disparar CompletionException caso seja enviada uma exception")
	void deveDispararCompletionExceptionCasoSejaEnviadaUmaException() {
		String result = "result";
		assertThrows(CompletionException.class, () -> KafkaHandler.handle("Erro").apply(result, new Exception()));
	}

}
