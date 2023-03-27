package com.oci.ed.http;

import com.oracle.bmc.emaildataplane.model.EmailAddress;
import com.oracle.bmc.emaildataplane.model.Recipients;
import com.oracle.bmc.emaildataplane.model.SubmitEmailDetails;
import com.oracle.bmc.emaildataplane.requests.SubmitEmailRequest;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.oci.ed.OCITenancyConfig.COMPARTMENT_ID;

public class HttpEmailSender {

    public static void main(String args[]) {

        if (args.length < 4) {
            System.out.println("Not enough arguments - Required : <FromAddress> <ToAddress> <Subject> <Region>");
        }
        String compartmentId = COMPARTMENT_ID;
        String fromAddress = args[0];
        String toAddress = args[1];
        String subject = args[2];
        String region = args[3];

        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = 0;
        try {
            responseCode = httpEmailSender.submitSDKEmail(
                    region,
                    httpEmailSender.buildEmailRequest(
                            compartmentId,
                            fromAddress,
                            toAddress,
                            subject)
            );
        } catch (IOException e) {
            System.out.println("HttpEmailSender@Main@SignedHttpEmail@Error >>> " + e.getMessage());
        }
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
    }

    public String handleRequest(String input) {
        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = httpEmailSender.submitSDKEmail("IAD", new HttpSubmitEmailRequest().buildHttpSubmitEmailRequest());
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
        return "Http Signed Email Sent : " + responseCode;
    }

    public SubmitEmailRequest buildEmailRequest(String compartmentId, String fromAddress, String toAddress, String subject) throws IOException {
        System.out.println("HttpEmailSender@buildEmailRequest@CompartmentId >>> " + COMPARTMENT_ID);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        SubmitEmailDetails submitEmailDetails = SubmitEmailDetails.builder()
                .subject(subject)
                .compartmentId(compartmentId)
                .recipients(Recipients.builder()
                        .to(Arrays.asList(EmailAddress.builder()
                                .email(toAddress)
                                .name("OCI ED Support")
                                .build()))
                        .build())
                .sender(EmailAddress.builder()
                        .email(fromAddress)
                        .name("OCI ED Support")
                        .build())
                .bodyText("Knock knock from OCI EMAIL SDK for Http Send API!!!")
                .bodyHtml(getHtmlBody())
                .headerFields(headers)
                .build();
        return SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();
    }

    public int submitSDKEmail(String region, SubmitEmailRequest submitEmailRequest) {
        int responseCode = -1;
        try {
            HttpEmailSDKClient httpEmailSDKClient = new HttpEmailSDKClient(region);
            responseCode = httpEmailSDKClient.submit(submitEmailRequest);
            System.out.println("HttpEmailSender@submitSDKEmail@HttpResponseCode >>> " + responseCode);
        } catch (Exception e) {
            System.out.println("HttpEmailSender@submitSDKEmail@Error >>> " + e);
        }
        return responseCode;
    }

    public String getHtmlBody() throws IOException {
        String htmlContent = null;
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("EvergyContent.html");
            try {
                htmlContent = IOUtils.toString(in, StandardCharsets.UTF_8);
            } finally {
                IOUtils.closeQuietly(in);
            }
        } catch (Exception e) {
            System.out.println("HttpEmailSender@getHtmlBody@Error >>> " + e);
            throw e;
        }
        System.out.println("HttpEmailSender@getHtmlBody@HtmlContent >>> " + htmlContent);
        return htmlContent;
    }
}
