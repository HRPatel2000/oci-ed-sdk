package com.oci.ed.http.test;

import com.oci.ed.http.HttpEmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HttpEmailClientSenderTest {

    HttpEmailSender httpEmailSender;

    @BeforeEach
    void setUp() {
        httpEmailSender = new HttpEmailSender();
    }

    @Test
    @DisplayName("Should send email via Http Endpoint")
    void shouldSubmitEmailViaHttpEndpoint() {
        assertNotEquals(-1, httpEmailSender.submitSDKEmail());
    }
}