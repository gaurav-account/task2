package com.example.task2.react;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.task2.bean.Foo;
import com.example.task2.service.FooService;

import reactor.core.publisher.Mono;

@Component
public class FooHandler {
    
    @Autowired
    FooService fooService;
    
    public Mono<ServerResponse> getFoos(ServerRequest request) {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(fooService.getFoos(),Foo.class);
        
    }

}
