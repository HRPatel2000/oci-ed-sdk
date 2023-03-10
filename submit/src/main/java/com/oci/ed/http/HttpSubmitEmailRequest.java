package com.oci.ed.http;

import com.oracle.bmc.submitemail.model.EmailAddress;
import com.oracle.bmc.submitemail.model.Recipients;
import com.oracle.bmc.submitemail.model.SubmitEmailDetails;
import com.oracle.bmc.submitemail.requests.SubmitEmailRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpSubmitEmailRequest {

    private static final String COMPARTMENT_ID = "ocid1.tenancy.oc1..aaaaaaaapgqj5fjeku52qogywp4agshdscgog4gaeuk5uxfccyx5fofpg2oa";

    public SubmitEmailRequest buildHttpSubmitEmailRequest() {
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
}
