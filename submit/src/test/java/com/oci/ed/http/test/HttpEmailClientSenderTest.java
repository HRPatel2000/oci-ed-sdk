package com.oci.ed.http.test;

import com.oci.ed.http.HttpEmailSender;
import com.oracle.bmc.emaildataplane.model.EmailAddress;
import com.oracle.bmc.emaildataplane.model.Recipients;
import com.oracle.bmc.emaildataplane.model.SubmitEmailDetails;
import com.oracle.bmc.emaildataplane.requests.SubmitEmailRequest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.oci.ed.OCITenancyConfig.COMPARTMENT_ID;
import static org.junit.Assert.assertEquals;

class HttpEmailClientSenderTest {

    HttpEmailSender httpEmailSender;

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
                                .build(), EmailAddress.builder()
                                .email("test@123.com")
                                .name("OCI ED Support")
                                .build(), EmailAddress.builder()
                                .email("foo@bar.bar.local")
                                .name("OCI ED Support")
                                .build()))
                        .build())
                .sender(EmailAddress.builder()
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
                .sender(EmailAddress.builder()
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
                .sender(EmailAddress.builder()
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

    @Test
    @DisplayName("Should send Html email via Http Endpoint")
    void shouldSubmitHTMLEmailViaHttpEndpoint() {
        String htmlContent = null;
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("./BasicForm.html");
            try {
                htmlContent = IOUtils.toString(in, StandardCharsets.UTF_8);
            } finally {
                IOUtils.closeQuietly(in);
            }
        } catch (Exception e) {
            System.out.println("HttpEmailClientSenderTest@shouldSubmitHTMLEmailViaHttpEndpoint@Error >>> " + e);
        }
        System.out.println("HttpEmailClientSenderTest@shouldSubmitHTMLEmailViaHttpEndpoint@HtmlContent >>> " + htmlContent);

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
                .sender(EmailAddress.builder()
                        .email("abc@test.com")
                        .name("OCI ED Support")
                        .build())
                .bodyText("Knock knock from OCI EMAIL SDK for Http Send API!!!")
                .bodyHtml(htmlContent)
                .headerFields(headers)
                .build();
        SubmitEmailRequest submitEmailRequest = SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();
        assertEquals(200, httpEmailSender.submitSDKEmail(submitEmailRequest));
    }
}