package com.oci.ed.track;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.email.EmailClient;
import com.oracle.bmc.email.model.CreateEmailTrackConfigDetails;
import com.oracle.bmc.email.model.EmailTrackConfig;
import com.oracle.bmc.email.model.EmailTrackConfigSummary;
import com.oracle.bmc.email.model.UpdateEmailTrackConfigDetails;
import com.oracle.bmc.email.requests.*;
import com.oracle.bmc.email.responses.*;
import com.oracle.bmc.model.BmcException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.oci.ed.OCITenancyConfig.REGION;
import static com.oci.ed.OCITenancyConfig.TRACK_ENDPOINT;

public class TrackEmailSDKClient {

    private static final String CONFIG_LOCATION = ".oci/config";
    private static final String CONFIG_PROFILE = "IAD";

    public TrackConfigResponse createTrackConfigId(String trackConfigScopeId, String compartmentId, boolean openTrackingEnabled, boolean clickTrackingEnabled) {
        TrackConfigResponse trackConfigResponse = null;
        try {
            EmailClient emailClient = getEmailClient();

            CreateEmailTrackConfigRequest request = CreateEmailTrackConfigRequest.builder()
                    .createEmailTrackConfigDetails(CreateEmailTrackConfigDetails.builder()
                            .trackConfigScopeId(trackConfigScopeId)
                            .compartmentId(compartmentId)
                            .isOpenTrackingEnabled(openTrackingEnabled)
                            .isClickTrackingEnabled(clickTrackingEnabled)
                            .build())
                    .build();
            CreateEmailTrackConfigResponse response = emailClient.createEmailTrackConfig(request);
            trackConfigResponse = getTrackConfigResponse(response.get__httpStatusCode__(), response.getEmailTrackConfig());

        } catch (BmcException e) {
            System.out.println("TrackEmailSDKClient@createTrackConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@createTrackConfigId@Error >>> " + e);
        }
        return trackConfigResponse;
    }

    public int updateTrackConfigId(String trackConfigId, boolean openTrackingEnabled, boolean clickTrackingEnabled) {
        int httpStatusCode = -1;
        try {
            EmailClient emailClient = getEmailClient();

            UpdateEmailTrackConfigRequest request = UpdateEmailTrackConfigRequest.builder()
                    .updateEmailTrackConfigDetails(UpdateEmailTrackConfigDetails.builder()
                            .isOpenTrackingEnabled(openTrackingEnabled)
                            .isClickTrackingEnabled(clickTrackingEnabled)
                            .build())
                    .emailTrackConfigId(trackConfigId)
                    .build();

            UpdateEmailTrackConfigResponse response = emailClient.updateEmailTrackConfig(request);
            httpStatusCode = response.get__httpStatusCode__();

            System.out.println("TrackEmailSDKClient@updateTrackConfigId@HttpResponseCode >>> " + httpStatusCode);
        } catch (BmcException e) {
            httpStatusCode = e.getStatusCode();
            System.out.println("TrackEmailSDKClient@updateTrackConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@updateTrackConfigId@Error >>> " + e);
        }
        return httpStatusCode;
    }

    public int deleteTrackConfigId(String trackConfigId) {
        int httpStatusCode = -1;
        try {
            EmailClient emailClient = getEmailClient();

            DeleteEmailTrackConfigRequest request = DeleteEmailTrackConfigRequest.builder()
                    .emailTrackConfigId(trackConfigId)
                    .build();

            DeleteEmailTrackConfigResponse response = emailClient.deleteEmailTrackConfig(request);
            httpStatusCode = response.get__httpStatusCode__();

            System.out.println("TrackEmailSDKClient@deleteTrackConfigId@HttpResponseCode >>> " + httpStatusCode);
        } catch (BmcException e) {
            httpStatusCode = e.getStatusCode();
            System.out.println("TrackEmailSDKClient@deleteTrackConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@deleteTrackConfigId@Error >>> " + e);
        }
        return httpStatusCode;
    }

    public TrackConfigResponse getByTrackConfigId(String trackConfigId) {
        TrackConfigResponse trackConfigResponse = null;
        try {
            EmailClient emailClient = getEmailClient();

            GetEmailTrackConfigRequest request = GetEmailTrackConfigRequest.builder()
                    .emailTrackConfigId(trackConfigId)
                    .build();
            GetEmailTrackConfigResponse response = emailClient.getEmailTrackConfig(request);
            trackConfigResponse = getTrackConfigResponse(response.get__httpStatusCode__(), response.getEmailTrackConfig());

        } catch (BmcException e) {
            System.out.println("TrackEmailSDKClient@getByTrackConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@getByTrackConfigId@Error >>> " + e);
        }
        return trackConfigResponse;
    }

    public List<TrackConfigResponse> listForEmailDomain(String trackScopeId) {
        final List<TrackConfigResponse> trackConfigResponses = new ArrayList<>();
        try {
            EmailClient emailClient = getEmailClient();

            ListEmailTrackConfigsRequest request = ListEmailTrackConfigsRequest.builder()
                    .trackConfigScopeId(trackScopeId)
                    .build();
            ListEmailTrackConfigsResponse response = emailClient.listEmailTrackConfigs(request);
            response.getEmailTrackConfigCollection().getItems().forEach(item -> trackConfigResponses.add(getTrackConfigResponse(response.get__httpStatusCode__(), item)));
        } catch (BmcException e) {
            System.out.println("TrackEmailSDKClient@listForEmailDomain@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@listForEmailDomain@Error >>> " + e);
        }
        return trackConfigResponses;
    }

    public List<TrackConfigResponse> listForCopartment(String compartmentId) {
        final List<TrackConfigResponse> trackConfigResponses = new ArrayList<>();
        try {
            EmailClient emailClient = getEmailClient();

            ListEmailTrackConfigsRequest request = ListEmailTrackConfigsRequest.builder()
                    .compartmentId(compartmentId)
                    .build();
            ListEmailTrackConfigsResponse response = emailClient.listEmailTrackConfigs(request);
            response.getEmailTrackConfigCollection().getItems().forEach(item -> trackConfigResponses.add(getTrackConfigResponse(response.get__httpStatusCode__(), item)));
        } catch (BmcException e) {
            System.out.println("TrackEmailSDKClient@listForCopartment@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("TrackEmailSDKClient@listForCopartment@Error >>> " + e);
        }
        return trackConfigResponses;
    }

    private EmailClient getEmailClient() throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
        EmailClient emailClient = EmailClient.builder()
                .region(REGION)
                .endpoint(TRACK_ENDPOINT)
                .build(provider);

        System.out.println("TrackEmailSDKClient@createTrackConfigId@Endpoint >>> " + emailClient.getEndpoint());
        return emailClient;
    }

    private TrackConfigResponse getTrackConfigResponse(int httpStatusCode, EmailTrackConfig response) {
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@HttpResponseCode >>> " + httpStatusCode);
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@CompartmentId >>> " + response.getCompartmentId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@TrackConfigScopeId >>> " + response.getTrackConfigScopeId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@Id >>> " + response.getId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@OpenTrackingEnabled >>> " + response.getIsOpenTrackingEnabled());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@ClickTrackingEnabled >>> " + response.getIsClickTrackingEnabled());

        TrackConfigResponse trackConfig;
        trackConfig = new TrackConfigResponse(
                httpStatusCode,
                response.getTrackConfigScopeId(),
                response.getCompartmentId(),
                response.getId(),
                response.getIsOpenTrackingEnabled()
        );
        return trackConfig;
    }

    private TrackConfigResponse getTrackConfigResponse(int httpStatusCode, EmailTrackConfigSummary response) {
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@HttpResponseCode >>> " + httpStatusCode);
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@CompartmentId >>> " + response.getCompartmentId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@TrackConfigScopeId >>> " + response.getTrackConfigScopeId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@Id >>> " + response.getId());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@OpenTrackingEnabled >>> " + response.getIsOpenTrackingEnabled());
        System.out.println("TrackEmailSDKClient@getTrackConfigResponse@ClickTrackingEnabled >>> " + response.getIsClickTrackingEnabled());

        TrackConfigResponse trackConfig;
        trackConfig = new TrackConfigResponse(
                httpStatusCode,
                response.getTrackConfigScopeId(),
                response.getCompartmentId(),
                response.getId(),
                response.getIsOpenTrackingEnabled()
        );
        return trackConfig;
    }
}
