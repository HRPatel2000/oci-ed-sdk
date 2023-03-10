package com.oci.ed.opens;

public class TrackConfigResponse {
    int httpStatusCode = -1;
    private String trackConfigScopeId;
    private String compartmentId;
    private String trackConfigId;
    private boolean isOpenTrackingEnabled;

    public TrackConfigResponse(int httpResponseCode, String trackConfigScopeId, String compartmentId, String id, boolean isOpenTrackingEnabled) {
        this.httpStatusCode = httpResponseCode;
        this.trackConfigScopeId = trackConfigScopeId;
        this.compartmentId = compartmentId;
        this.trackConfigId = id;
        this.isOpenTrackingEnabled = isOpenTrackingEnabled;
    }

    public String getTrackConfigScopeId() {
        return trackConfigScopeId;
    }

    public void setTrackConfigScopeId(String trackConfigScopeId) {
        this.trackConfigScopeId = trackConfigScopeId;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(String compartmentId) {
        this.compartmentId = compartmentId;
    }

    public String getTrackConfigId() {
        return trackConfigId;
    }

    public void setTrackConfigId(String trackConfigId) {
        this.trackConfigId = trackConfigId;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public boolean isOpenTrackingEnabled() {
        return isOpenTrackingEnabled;
    }

    public void setClickTrackingEnabled(boolean clickTrackingEnabled) {
        isOpenTrackingEnabled = clickTrackingEnabled;
    }
}
