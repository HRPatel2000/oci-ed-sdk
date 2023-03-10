package com.oci.ed.opens;

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

import java.util.ArrayList;
import java.util.List;

public class OpensEmailSDKClient {

    private static final String CONFIG_LOCATION = ".oci/config";
    private static final String CONFIG_PROFILE = "IAD";

    public TrackConfigResponse createOpenConfigId(String trackConfigScopeId, String compartmentId, boolean clickTrackingEnabled) {
        TrackConfigResponse trackConfigResponse = null;
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@createOpenConfigId@Endpoint >>> " + emailClient.getEndpoint());

            CreateEmailTrackConfigRequest request = CreateEmailTrackConfigRequest.builder()
                    .createEmailTrackConfigDetails(CreateEmailTrackConfigDetails.builder()
                            .trackConfigScopeId(trackConfigScopeId)
                            .compartmentId(compartmentId)
                            .isOpenTrackingEnabled(clickTrackingEnabled)
                            .build())
                    .build();
            CreateEmailTrackConfigResponse response = emailClient.createEmailTrackConfig(request);
            trackConfigResponse = getTrackConfigResponse(response.get__httpStatusCode__(), response.getEmailTrackConfig());

        } catch (BmcException e) {
            System.out.println("OpensEmailSDKClient@createOpenConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@createOpenConfigId@Error >>> " + e);
        }
        return trackConfigResponse;
    }

    public int updateOpenConfigId(String trackConfigId, boolean clickTrackingEnabled) {
        int httpStatusCode = -1;
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@getOpenConfigId@Endpoint >>> " + emailClient.getEndpoint());

            UpdateEmailTrackConfigRequest request = UpdateEmailTrackConfigRequest.builder()
                    .updateEmailTrackConfigDetails(UpdateEmailTrackConfigDetails.builder()
                            .isOpenTrackingEnabled(clickTrackingEnabled)
                            .build())
                    .emailTrackConfigId(trackConfigId)
                    .build();

            UpdateEmailTrackConfigResponse response = emailClient.updateEmailTrackConfig(request);
            httpStatusCode = response.get__httpStatusCode__();

            System.out.println("OpensEmailSDKClient@updateOpenConfigId@HttpResponseCode >>> " + httpStatusCode);
        } catch (BmcException e) {
            httpStatusCode = e.getStatusCode();
            System.out.println("OpensEmailSDKClient@updateOpenConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@updateOpenConfigId@Error >>> " + e);
        }
        return httpStatusCode;
    }

    public int deleteOpenConfigId(String trackConfigId) {
        int httpStatusCode = -1;
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@deleteOpenConfigId@Endpoint >>> " + emailClient.getEndpoint());

            DeleteEmailTrackConfigRequest request = DeleteEmailTrackConfigRequest.builder()
                    .emailTrackConfigId(trackConfigId)
                    .build();

            DeleteEmailTrackConfigResponse response = emailClient.deleteEmailTrackConfig(request);
            httpStatusCode = response.get__httpStatusCode__();

            System.out.println("OpensEmailSDKClient@deleteOpenConfigId@HttpResponseCode >>> " + httpStatusCode);
        } catch (BmcException e) {
            httpStatusCode = e.getStatusCode();
            System.out.println("OpensEmailSDKClient@deleteOpenConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@deleteOpenConfigId@Error >>> " + e);
        }
        return httpStatusCode;
    }

    public TrackConfigResponse getByTrackConfigId(String trackConfigId) {
        TrackConfigResponse trackConfigResponse = null;
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@getByTrackConfigId@Endpoint >>> " + emailClient.getEndpoint());

            GetEmailTrackConfigRequest request = GetEmailTrackConfigRequest.builder()
                    .emailTrackConfigId(trackConfigId)
                    .build();
            GetEmailTrackConfigResponse response = emailClient.getEmailTrackConfig(request);
            trackConfigResponse = getTrackConfigResponse(response.get__httpStatusCode__(), response.getEmailTrackConfig());

        } catch (BmcException e) {
            System.out.println("OpensEmailSDKClient@getByTrackConfigId@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@getByTrackConfigId@Error >>> " + e);
        }
        return trackConfigResponse;
    }

    public List<TrackConfigResponse> listForEmailDomain(String trackScopeId) {
        final List<TrackConfigResponse> trackConfigResponses = new ArrayList<>();
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@listForEmailDomain@Endpoint >>> " + emailClient.getEndpoint());

            ListEmailTrackConfigsRequest request = ListEmailTrackConfigsRequest.builder()
                    .trackConfigScopeId(trackScopeId)
                    .build();
            ListEmailTrackConfigsResponse response = emailClient.listEmailTrackConfigs(request);
            response.getEmailTrackConfigCollection().getItems().forEach(item -> trackConfigResponses.add(getTrackConfigResponse(response.get__httpStatusCode__(), item)));
        } catch (BmcException e) {
            System.out.println("OpensEmailSDKClient@listForEmailDomain@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@listForEmailDomain@Error >>> " + e);
        }
        return trackConfigResponses;
    }

    public List<TrackConfigResponse> listForCopartment(String compartmentId) {
        final List<TrackConfigResponse> trackConfigResponses = new ArrayList<>();
        try {
            final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
            final AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(configFile);
            EmailClient emailClient = EmailClient.builder()
                    .region(Region.US_ASHBURN_1)
                    .endpoint("https://ctrl.email.us-ashburn-1.oci.oraclecloud.com")
                    .build(provider);
            System.out.println("OpensEmailSDKClient@listForCopartment@Endpoint >>> " + emailClient.getEndpoint());

            ListEmailTrackConfigsRequest request = ListEmailTrackConfigsRequest.builder()
                    .compartmentId(compartmentId)
                    .build();
            ListEmailTrackConfigsResponse response = emailClient.listEmailTrackConfigs(request);
            response.getEmailTrackConfigCollection().getItems().forEach(item -> trackConfigResponses.add(getTrackConfigResponse(response.get__httpStatusCode__(), item)));
        } catch (BmcException e) {
            System.out.println("OpensEmailSDKClient@listForCopartment@BMCError >>> " + e);
        } catch (Exception e) {
            System.out.println("OpensEmailSDKClient@listForCopartment@Error >>> " + e);
        }
        return trackConfigResponses;
    }

    private TrackConfigResponse getTrackConfigResponse(int httpStatusCode, EmailTrackConfig response) {
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@HttpResponseCode >>> " + httpStatusCode);
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@CompartmentId >>> " + response.getCompartmentId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@TrackConfigScopeId >>> " + response.getTrackConfigScopeId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@Id >>> " + response.getId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@ClickTrackingEnabled >>> " + response.getIsOpenTrackingEnabled());

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
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@HttpResponseCode >>> " + httpStatusCode);
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@CompartmentId >>> " + response.getCompartmentId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@TrackConfigScopeId >>> " + response.getTrackConfigScopeId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@Id >>> " + response.getId());
        System.out.println("OpensEmailSDKClient@getTrackConfigResponse@ClickTrackingEnabled >>> " + response.getIsOpenTrackingEnabled());

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
