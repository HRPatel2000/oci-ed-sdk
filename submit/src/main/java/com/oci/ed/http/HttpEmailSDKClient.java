package com.oci.ed.http;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.model.BmcException;
import com.oracle.bmc.emaildataplane.EmailDPClient;
import com.oracle.bmc.emaildataplane.model.EmailAddress;
import com.oracle.bmc.emaildataplane.requests.SubmitEmailRequest;
import com.oracle.bmc.emaildataplane.responses.SubmitEmailResponse;

import java.io.IOException;
import java.util.List;

import static com.oci.ed.OCITenancyConfig.REGION;
import static com.oci.ed.OCITenancyConfig.TRACK_ENDPOINT;

public class HttpEmailSDKClient {

    private static final String CONFIG_LOCATION = ".oci/config";
    private static final String CONFIG_PROFILE = "IAD";

    public int submit(SubmitEmailRequest submitEmailRequest) {
        int responseCode = -1;
        try {
            EmailDPClient emailClient = getEmailClient();

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

    private EmailDPClient getEmailClient() throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
        EmailDPClient emailClient = EmailDPClient.builder()
                .region(REGION)
                .endpoint(TRACK_ENDPOINT)
                .build(provider);

        System.out.println("TrackEmailSDKClient@createTrackConfigId@Endpoint >>> " + emailClient.getEndpoint());
        return emailClient;
    }
}
