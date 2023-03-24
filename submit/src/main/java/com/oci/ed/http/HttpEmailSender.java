package com.oci.ed.http;

import com.oracle.bmc.emaildataplane.requests.SubmitEmailRequest;

public class HttpEmailSender {

    public static void main (String args[]) {
        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = httpEmailSender.submitSDKEmail(new HttpSubmitEmailRequest().buildHttpSubmitEmailRequest());
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
    }

    public String handleRequest(String input) {

        HttpEmailSender httpEmailSender = new HttpEmailSender();
        int responseCode = httpEmailSender.submitSDKEmail(new HttpSubmitEmailRequest().buildHttpSubmitEmailRequest());
        System.out.println("HttpEmailSender@Main@SignedHttpEmail@ResponseCode >>> " + responseCode);
        return "Http Signed Email Sent : " + responseCode;
    }

    public int submitSDKEmail(SubmitEmailRequest submitEmailRequest) {
        int responseCode = -1;
        try {
            HttpEmailSDKClient httpEmailSDKClient = new HttpEmailSDKClient();
            responseCode = httpEmailSDKClient.submit(submitEmailRequest);
            System.out.println("HttpEmailSender@submitSDKEmail@HttpResponseCode >>> " + responseCode);
        } catch (Exception e) {
            System.out.println("HttpEmailSender@submitSDKEmail@Error >>> " + e);
        }
        return responseCode;
    }
}
