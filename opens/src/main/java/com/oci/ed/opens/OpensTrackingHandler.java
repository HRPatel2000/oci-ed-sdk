package com.oci.ed.opens;

import java.util.List;

import static com.oci.ed.opens.OCITenancyConfig.COMPARTMENT_ID;
import static com.oci.ed.opens.OCITenancyConfig.TRACK_CONFIG_SCOPE_ID;

public class OpensTrackingHandler {
    private static String TRACK_CONFIG_ID = null;

    public static void main(String args[]) {
       String trackConfigId = fireOpensTracking();
        System.out.println("OpensTrackingHandler@main@TrackConfigID >>> " + trackConfigId);
    }

    public String handleRequest(String input) {
        String trackConfigId = fireOpensTracking();
        System.out.println("OpensTrackingHandler@handleRequest@TrackConfigID >>> " + trackConfigId);
        return "Created, Updated and Finally Deleted TrackConfigID via OCI Function and Email SDK >>> " + trackConfigId;
    }

    public static String fireOpensTracking() {
        OpensEmailSDKClient opensEmailSDKClient = new OpensEmailSDKClient();
        TrackConfigResponse trackConfigResponse = opensEmailSDKClient.createOpenConfigId(TRACK_CONFIG_SCOPE_ID, COMPARTMENT_ID, true);
        String trackConfigId = trackConfigResponse.getTrackConfigId();
        System.out.println("OpensTrackingHandler@fireOpensTracking@CreateTrackingConfig@ResponseCode >>> " + trackConfigResponse.httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trackConfigResponse = opensEmailSDKClient.getByTrackConfigId(TRACK_CONFIG_ID);
        System.out.println("OpensTrackingHandler@fireOpensTracking@GetByID@ResponseCode >>> " + trackConfigResponse.httpStatusCode);

        int httpStatusCode = opensEmailSDKClient.updateOpenConfigId(TRACK_CONFIG_ID, false);
        System.out.println("OpensTrackingHandler@fireOpensTracking@UpdateTrackingConfig@ResponseCode >>> " + httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<TrackConfigResponse> trackConfigResponses = opensEmailSDKClient.listForEmailDomain(TRACK_CONFIG_SCOPE_ID);
        System.out.println("OpensTrackingHandler@fireOpensTracking@ListForEmailDomain@Size >>> " + trackConfigResponses.size());

        trackConfigResponses = opensEmailSDKClient.listForCopartment(COMPARTMENT_ID);
        System.out.println("OpensTrackingHandler@fireOpensTracking@ListForCompartment@Size >>> " + trackConfigResponses.size());

        httpStatusCode = opensEmailSDKClient.deleteOpenConfigId(TRACK_CONFIG_ID);
        System.out.println("OpensTrackingHandler@fireOpensTracking@DeleteByID@ResponseCode >>> " + httpStatusCode);
        return trackConfigId;
    }
}
