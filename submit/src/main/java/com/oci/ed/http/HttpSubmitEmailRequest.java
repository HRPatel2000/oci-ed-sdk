package com.oci.ed.http;

import com.oracle.bmc.emaildataplane.model.EmailAddress;
import com.oracle.bmc.emaildataplane.model.Recipients;
import com.oracle.bmc.emaildataplane.model.SubmitEmailDetails;
import com.oracle.bmc.emaildataplane.requests.SubmitEmailRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.oci.ed.OCITenancyConfig.COMPARTMENT_ID;

public class HttpSubmitEmailRequest {

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
                .sender(EmailAddress.builder()
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
