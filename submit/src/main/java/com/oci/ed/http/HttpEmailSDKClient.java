package com.oci.ed.http;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.model.BmcException;
import com.oracle.bmc.submitemail.EmailClient;
import com.oracle.bmc.submitemail.model.EmailAddress;
import com.oracle.bmc.submitemail.requests.SubmitEmailRequest;
import com.oracle.bmc.submitemail.responses.SubmitEmailResponse;

import java.util.List;

public class HttpEmailSDKClient {

    private static final String CONFIG_LOCATION = ".oci/config";
    private static final String CONFIG_PROFILE = "IAD";

    public int submit(SubmitEmailRequest submitEmailRequest) {
        int responseCode = -1;
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://cell0.submit.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("HttpEmailSender@getEmailClient@SmtpEndpoint >>> " + emailClient.getEndpoint());

            SubmitEmailResponse submitEmailResponse = emailClient.submitEmail(submitEmailRequest);
            responseCode = submitEmailResponse.get__httpStatusCode__();
            List<EmailAddress> suppressedRecipients = submitEmailResponse.getEmailSubmittedResponse().getSuppressedRecipients();
            for (EmailAddress suppressedEmail : suppressedRecipients) {
                System.out.println("HttpEmailSender@submitHttpEmail@SuppressedEmail >>> " + suppressedEmail.getEmail());
            }
            System.out.println("HttpEmailSender@submitHttpEmail@HttpResponseCode >>> " + submitEmailResponse.get__httpStatusCode__());
            System.out.println("HttpEmailSender@submitHttpEmail@MessageId >>> " + submitEmailResponse.getEmailSubmittedResponse().getMessageId());
            System.out.println("HttpEmailSender@submitHttpEmail@EnvelopeId >>> " + submitEmailResponse.getEmailSubmittedResponse().getEnvelopeId());
        } catch (BmcException e) {
            System.out.println("HttpEmailSender@submitHttpEmail@BMCError >>> " + e);
            responseCode = e.getStatusCode();
        } catch (Exception e) {
            System.out.println("HttpEmailSender@submitHttpEmail@Error >>> " + e);
        }
        return responseCode;
    }
}
