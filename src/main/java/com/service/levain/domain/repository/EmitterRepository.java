package com.service.levain.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void save(String userName, SseEmitter emitter) {
        emitters.put(userName, emitter);
    }

    public void deleteById(String userName) {
        emitters.remove(userName);
    }

    public SseEmitter get(String userName) {
        return emitters.get(userName);
    }
}
