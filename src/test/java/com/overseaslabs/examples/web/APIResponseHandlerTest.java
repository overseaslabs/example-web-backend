package com.overseaslabs.examples.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class APIResponseHandlerTest {
    @Autowired
    private APIResponseHandler handler;

    @MockBean
    private ClientHttpResponse response;

    @Test
    void testHasError() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);
        assertTrue(handler.hasError(response));
    }

    @Test
    void testDoesntHaveError() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatus.OK);
        assertFalse(handler.hasError(response));
    }

    @Test
    void testHandleError() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);
        when(response.getStatusText()).thenReturn("Error");

        handler.handleError(response);
    }
}
