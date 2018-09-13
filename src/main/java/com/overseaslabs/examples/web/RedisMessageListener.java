package com.overseaslabs.examples.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overseaslabs.examples.mailer.entity.ProviderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

public class RedisMessageListener implements MessageListener {

    @Autowired
    ObjectMapper om;

    @Autowired
    private SimpMessagingTemplate template;

    public void onMessage(Message message, byte[] pattern) {
        ProviderResponse response = null;

        try {
            response = om.readValue(message.getBody(), ProviderResponse.class);
            //push to the websocket
            template.convertAndSend("/topic/mailer", response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
