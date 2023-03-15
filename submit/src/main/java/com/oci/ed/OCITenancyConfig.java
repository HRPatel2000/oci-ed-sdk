package com.oci.ed;

import com.oracle.bmc.Region;

public interface OCITenancyConfig {
    //    INTC MUMBAI
//    Region REGION = Region.AP_MUMBAI_1;
//    String COMPARTMENT_ID = "<<COMPARTMENT_OCID>>";
//    String HTTP_SEND_ENDPOINT = https://cell0.submit-intc.email.ap-mumbai-1.oci.oraclecloud.com";

    //    IAD
    Region REGION = Region.US_ASHBURN_1;
    String COMPARTMENT_ID = "<<COMPARTMENT_OCID>>";
    String HTTP_SEND_ENDPOINT = "https://cell0.submit.email.us-ashburn-1.oci.oraclecloud.com";
}
