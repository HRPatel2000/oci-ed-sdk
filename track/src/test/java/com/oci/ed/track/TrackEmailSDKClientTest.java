package com.oci.ed.track;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static com.oci.ed.OCITenancyConfig.COMPARTMENT_ID;
import static com.oci.ed.OCITenancyConfig.TRACK_CONFIG_SCOPE_ID;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrackEmailSDKClientTest {

    private TrackEmailSDKClient trackEmailSDKClient = new TrackEmailSDKClient();
    private static String TRACK_CONFIG_ID = null;

    @Test
    @Order(1)
    void createTrackConfigId() {
        TrackConfigResponse trackConfigResponse = trackEmailSDKClient.createTrackConfigId(TRACK_CONFIG_SCOPE_ID, COMPARTMENT_ID, true, true);
        assertEquals(true, trackConfigResponse.isOpenTrackingEnabled());
        assertEquals(COMPARTMENT_ID, trackConfigResponse.getCompartmentId());
        assertEquals(TRACK_CONFIG_SCOPE_ID, trackConfigResponse.getTrackConfigScopeId());
        TRACK_CONFIG_ID = trackConfigResponse.getTrackConfigId();
        assertNotNull(trackConfigResponse.getTrackConfigId());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void getByTrackConfigId() {
        TrackConfigResponse trackConfigResponse = trackEmailSDKClient.getByTrackConfigId(TRACK_CONFIG_ID);
        assertEquals(COMPARTMENT_ID, trackConfigResponse.getCompartmentId());
        assertEquals(TRACK_CONFIG_SCOPE_ID, trackConfigResponse.getTrackConfigScopeId());
        assertEquals(TRACK_CONFIG_ID, trackConfigResponse.getTrackConfigId());
    }

    @Test
    @Order(3)
    void updateTrackConfigId() {
        int httpStatusCode = trackEmailSDKClient.updateTrackConfigId(TRACK_CONFIG_ID, false, false);
        assertEquals(202, httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void listForEmailDomain() {
        List<TrackConfigResponse> trackConfigResponses = trackEmailSDKClient.listForEmailDomain(TRACK_CONFIG_SCOPE_ID);
        assertNotNull(trackConfigResponses);
        assertNotEquals(0, trackConfigResponses.size());
        trackConfigResponses.forEach(item -> assertEquals(TRACK_CONFIG_SCOPE_ID, item.getTrackConfigScopeId()));
    }

    @Test
    @Order(5)
    void listForCopartment() {
        List<TrackConfigResponse> trackConfigResponses = trackEmailSDKClient.listForCopartment(COMPARTMENT_ID);
        assertNotNull(trackConfigResponses);
        assertNotEquals(0, trackConfigResponses.size());
        trackConfigResponses.forEach(item -> assertEquals(COMPARTMENT_ID, item.getCompartmentId()));
    }

    @Test
    @Order(6)
    void deleteOpenConfigId() {
        int httpStatusCode = trackEmailSDKClient.deleteTrackConfigId(TRACK_CONFIG_ID);
        assertEquals(202, httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}