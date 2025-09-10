package com.poc.fraude_service.outbound.kafka.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;

@Slf4j
@Data
public class KafkaHandler {

	public static <T> BiFunction<T, Throwable, T> handle(String errorMessage) {
		return (res, ex) -> {
			if (ex == null) {
				return res;
			}
			log.error(errorMessage, ex);
			throw new CompletionException(ex);
		};
	}

}
