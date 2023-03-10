package com.oci.ed.opens;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OpensEmailSDKClientTest {

    private OpensEmailSDKClient opensEmailSDKClient = new OpensEmailSDKClient();
    private static final String COMPARTMENT_ID = "ocid1.tenancy.oc1..aaaaaaaapgqj5fjeku52qogywp4agshdscgog4gaeuk5uxfccyx5fofpg2oa";
    private static final String TRACK_CONFIG_SCOPE_ID = "ocid1.emaildomain.oc1.iad.amaaaaaaooyoulaa34fycy4aht2rg2yr665ms5xcm3o2pzemn5rgy7xxrpya";
    private static String TRACK_CONFIG_ID = null;

    @Test
    @Order(1)
    void createOpenConfigId() {
        TrackConfigResponse trackConfigResponse = opensEmailSDKClient.createOpenConfigId(TRACK_CONFIG_SCOPE_ID, COMPARTMENT_ID, true);
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
        TrackConfigResponse trackConfigResponse = opensEmailSDKClient.getByTrackConfigId(TRACK_CONFIG_ID);
        assertEquals(COMPARTMENT_ID, trackConfigResponse.getCompartmentId());
        assertEquals(TRACK_CONFIG_SCOPE_ID, trackConfigResponse.getTrackConfigScopeId());
        assertEquals(TRACK_CONFIG_ID, trackConfigResponse.getTrackConfigId());
    }

    @Test
    @Order(3)
    void updateOpenConfigId() {
        int httpStatusCode = opensEmailSDKClient.updateOpenConfigId(TRACK_CONFIG_ID, false);
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
        List<TrackConfigResponse> trackConfigResponses = opensEmailSDKClient.listForEmailDomain(TRACK_CONFIG_SCOPE_ID);
        assertNotNull(trackConfigResponses);
        assertNotEquals(0, trackConfigResponses.size());
        trackConfigResponses.forEach(item -> assertEquals(TRACK_CONFIG_SCOPE_ID, item.getTrackConfigScopeId()));
    }

    @Test
    @Order(5)
    void listForCopartment() {
        List<TrackConfigResponse> trackConfigResponses = opensEmailSDKClient.listForCopartment(COMPARTMENT_ID);
        assertNotNull(trackConfigResponses);
        assertNotEquals(0, trackConfigResponses.size());
        trackConfigResponses.forEach(item -> assertEquals(COMPARTMENT_ID, item.getCompartmentId()));
    }

    @Test
    @Order(6)
    void deleteOpenConfigId() {
        int httpStatusCode = opensEmailSDKClient.deleteOpenConfigId(TRACK_CONFIG_ID);
        assertEquals(202, httpStatusCode);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}