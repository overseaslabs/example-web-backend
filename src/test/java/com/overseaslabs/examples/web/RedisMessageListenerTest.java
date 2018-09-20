package com.overseaslabs.examples.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overseaslabs.examples.mailer.entity.ProviderResponse;
import com.overseaslabs.examples.ureg.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RedisMessageListenerTest {
    @MockBean
    private ObjectMapper om;

    @Autowired
    private RedisMessageListener listener;

    @MockBean
    private SimpMessagingTemplate template;

    @Test
    void testOnMessage() throws IOException {
        ProviderResponse providerResponse=new ProviderResponse();

        when(om.readValue(any(byte[].class), eq(ProviderResponse.class))).thenReturn(providerResponse);

        listener.onMessage(new Message() {
            @Override
            public byte[] getBody() {
                return new byte[0];
            }

            @Override
            public byte[] getChannel() {
                return new byte[0];
            }
        }, new byte[]{1, 2, 3});

        verify(om, times(1)).readValue(any(byte[].class), eq(ProviderResponse.class));
        verify(template, times(1)).convertAndSend(eq("/topic/mailer"), eq(providerResponse));
    }
}
