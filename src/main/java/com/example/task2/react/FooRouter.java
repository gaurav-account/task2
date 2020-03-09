package com.example.task2.react;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class FooRouter {

    @Bean
    public RouterFunction<ServerResponse> route(FooHandler fooHandler) {
        return RouterFunctions
        		.route(RequestPredicates.GET("/foo")
        				.and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), 
        				fooHandler::getFoos);
    }

}
