package com.oci.ed.http;

import com.oracle.bmc.submitemail.model.EmailAddress;
import com.oracle.bmc.submitemail.model.Recipients;
import com.oracle.bmc.submitemail.model.SubmitEmailDetails;
import com.oracle.bmc.submitemail.requests.SubmitEmailRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpEmailSender {

    private static final String COMPARTMENT_ID = "<<COMPARTMENT_OCID>>";

    public static void main (String args[]) {
        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = httpEmailSender.submitSDKEmail();
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
    }
    public String handleRequest(String input) {

        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = httpEmailSender.submitSDKEmail();
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
        return "Http Signed Email Sent : " + responseCode;
    }

    private SubmitEmailRequest buildEmailRequest() {
        System.out.println("HttpEmailSender@buildEmailRequest@CompartmentId >>> " + COMPARTMENT_ID);
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
        return SubmitEmailRequest.builder()
                .submitEmailDetails(submitEmailDetails)
                .build();
    }

    public int submitSDKEmail() {
        int responseCode = -1;
        try {
            HttpEmailSDKClient httpEmailSDKClient = new HttpEmailSDKClient();
            responseCode = httpEmailSDKClient.submit(buildEmailRequest());
            System.out.println("HttpEmailSender@submitSDKEmail@HttpResponseCode >>> " + responseCode);
        } catch (Exception e) {
            System.out.println("HttpEmailSender@submitSDKEmail@Error >>> " + e);
        }
        return responseCode;
    }
}
