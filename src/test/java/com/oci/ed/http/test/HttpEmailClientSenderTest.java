package com.oci.ed.http.test;

import com.oci.ed.http.HttpEmailSender;
import com.oracle.bmc.submitemail.model.EmailAddress;
import com.oracle.bmc.submitemail.model.Recipients;
import com.oracle.bmc.submitemail.model.SubmitEmailDetails;
import com.oracle.bmc.submitemail.requests.SubmitEmailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class HttpEmailClientSenderTest {

    HttpEmailSender httpEmailSender;
    private static final String COMPARTMENT_ID = "<<COMPARTMENT_ID>>";

    @BeforeEach
    void setUp() {
        httpEmailSender = new HttpEmailSender();
    }

    @Test
    @DisplayName("Should send email via Http Endpoint")
    void shouldSubmitEmailViaHttpEndpoint() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        SubmitEmailDetails submitEmailDetails = SubmitEmailDetails.builder()
                .subject("OCI Email SDK!!!")
                .compartmentId(COMPARTMENT_ID)
                .recipients(Recipients.builder()
                        .to(Arrays.asList(EmailAddress.builder()
                                .email("Himanshu.R.Patel@Oracle.com")
                                .name("OCI ED Support")
                                .build()))
                        .build())
                .from(EmailAddress.builder()
                        .email("abc@test.com")
                        .name("OCI ED Support")
                        .build())
                .bodyText("Knock knock from OCI EMAIL SDK for Http Send API!!!")
                .bodyHtml("Email SDK rocks!")
                .headerFields(headers)
                .build();
        SubmitEmailRequest submitEmailRequest = SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();
        assertEquals(200, httpEmailSender.submitSDKEmail(submitEmailRequest));
    }

    @Test
    @DisplayName("Should not send email via Http Endpoint when Sender is Unauthorized")
    void shouldThrow404ForUnAuthorizedApprovedSender() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        SubmitEmailDetails submitEmailDetails = SubmitEmailDetails.builder()
                .subject("OCI Email SDK!!!")
                .compartmentId(COMPARTMENT_ID)
                .recipients(Recipients.builder()
                        .to(Arrays.asList(EmailAddress.builder()
                                .email("Himanshu.R.Patel@Oracle.com")
                                .name("OCI ED Support")
                                .build()))
                        .build())
                .from(EmailAddress.builder()
                        .email("billionair@oci.com")
                        .name("OCI ED Support")
                        .build())
                .bodyText("Knock knock from OCI EMAIL SDK for Http Send API!!!")
                .bodyHtml("Email SDK rocks!")
                .headerFields(headers)
                .build();
        SubmitEmailRequest submitEmailRequest = SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();

        assertEquals(404, httpEmailSender.submitSDKEmail(submitEmailRequest));
    }

    @Test
    @DisplayName("Should not send email via Http Endpoint when Email Address is Invalid")
    void shouldThrow422ForInvalidEmailAddress() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        SubmitEmailDetails submitEmailDetails = SubmitEmailDetails.builder()
                .subject("OCI Email SDK!!!")
                .compartmentId(COMPARTMENT_ID)
                .recipients(Recipients.builder()
                        .to(Arrays.asList(EmailAddress.builder()
                                .email("IamINVALIDatOracle.com")
                                .name("OCI ED Support")
                                .build()))
                        .build())
                .from(EmailAddress.builder()
                        .email("abc@test.com")
                        .name("OCI ED Support")
                        .build())
                .bodyText("Knock knock from OCI EMAIL SDK for Http Send API!!!")
                .bodyHtml("Email SDK rocks!")
                .headerFields(headers)
                .build();
        SubmitEmailRequest submitEmailRequest = SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();

        assertEquals(422, httpEmailSender.submitSDKEmail(submitEmailRequest));
    }
}