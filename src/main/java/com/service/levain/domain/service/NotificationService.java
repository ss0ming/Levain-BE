package com.service.levain.domain.service;

import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.EmitterRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MemberRepository memberRepository;
    private final EmitterRepository emitterRepository;

    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;

    public SseEmitter subscribe(String userName) {
        SseEmitter emitter = createEmitter(userName);

        sendToClient(userName, "EventStream Created. [userName = " + userName + "]", "sse 접속 성공");
        return emitter;
    }

    pubilc <T> void customNotify(String userName, T data, String comment, String type) {
        sendToClient(userName, data, comment, type);
    }

    public void notify(String userName, Object data, String comment) {
        sendToClient(userName, data, comment);
    }

    private void sendToClient(String userName, Object data, String comment) {
        SseEmitter emitter = emitterRepository.get(userName);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(userName)
                        .name("sse")
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.deleteById(userName);
                emitter.completeWithError(e);
            }
        }
    }

    private <T> void sendToClient(String userName, T data, String comment, String type) {
        SseEmitter emitter = emitterRepository.get(userName);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(userName)
                        .name(type)
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.deleteById(userName);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(String userName) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(userName, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(userName));
        emitter.onTimeout(() -> emitterRepository.deleteById(userName));

        return emitter;
    }

    private Member validMember(String userName) {
        return memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
}
