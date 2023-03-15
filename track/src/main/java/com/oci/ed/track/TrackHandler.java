package com.oci.ed.track;

import java.util.List;

import static com.oci.ed.OCITenancyConfig.COMPARTMENT_ID;
import static com.oci.ed.OCITenancyConfig.TRACK_CONFIG_SCOPE_ID;

public class TrackHandler {
    private static String TRACK_CONFIG_ID = null;

    public static void main(String args[]) {
       String trackConfigId = runEmailTrackingLifecycle();
        System.out.println("TrackHandler@main@TrackConfigID >>> " + trackConfigId);
    }

    public String handleRequest(String input) {
        String trackConfigId = runEmailTrackingLifecycle();
        System.out.println("TrackHandler@handleRequest@TrackConfigID >>> " + trackConfigId);
        return "Created, Updated and Finally Deleted TrackConfigID via OCI Function and Email SDK >>> " + trackConfigId;
    }

    public static String runEmailTrackingLifecycle() {
        TrackEmailSDKClient opensEmailSDKClient = new TrackEmailSDKClient();
        TrackConfigResponse trackConfigResponse = opensEmailSDKClient.createTrackConfigId(TRACK_CONFIG_SCOPE_ID, COMPARTMENT_ID, true, true);
        String trackConfigId = trackConfigResponse.getTrackConfigId();
        System.out.println("TrackHandler@runEmailTrackingLifecycle@CreateTrackingConfig@ResponseCode >>> " + trackConfigResponse.httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trackConfigResponse = opensEmailSDKClient.getByTrackConfigId(TRACK_CONFIG_ID);
        System.out.println("TrackHandler@runEmailTrackingLifecycle@GetByID@ResponseCode >>> " + trackConfigResponse.httpStatusCode);

        int httpStatusCode = opensEmailSDKClient.updateTrackConfigId(TRACK_CONFIG_ID, false, false);
        System.out.println("TrackHandler@runEmailTrackingLifecycle@UpdateTrackingConfig@ResponseCode >>> " + httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<TrackConfigResponse> trackConfigResponses = opensEmailSDKClient.listForEmailDomain(TRACK_CONFIG_SCOPE_ID);
        System.out.println("TrackHandler@runEmailTrackingLifecycle@ListForEmailDomain@Size >>> " + trackConfigResponses.size());

        trackConfigResponses = opensEmailSDKClient.listForCopartment(COMPARTMENT_ID);
        System.out.println("TrackHandler@runEmailTrackingLifecycle@ListForCompartment@Size >>> " + trackConfigResponses.size());

        httpStatusCode = opensEmailSDKClient.deleteTrackConfigId(TRACK_CONFIG_ID);
        System.out.println("TrackHandler@runEmailTrackingLifecycle@DeleteByID@ResponseCode >>> " + httpStatusCode);
        return trackConfigId;
    }
}
