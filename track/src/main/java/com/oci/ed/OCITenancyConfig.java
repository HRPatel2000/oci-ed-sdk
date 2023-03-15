package com.oci.ed;

import com.oracle.bmc.Region;

public interface OCITenancyConfig {
    //    INTC MUMBAI
    Region REGION = Region.AP_MUMBAI_1;
    String COMPARTMENT_ID = "<<COMPARTMENT_OCID>>";
    String TRACK_CONFIG_SCOPE_ID = "<<EMAIL_DOMAIN_OCID>>";
    String TRACK_ENDPOINT = "https://ctrl-intc.email.ap-mumbai-1.oci.oraclecloud.com";

    //    IAD
//    Region REGION = Region.US_ASHBURN_1;
//    String COMPARTMENT_ID = "<<COMPARTMENT_OCID>>";
//    String TRACK_CONFIG_SCOPE_ID = "<<EMAIL_DOMAIN_OCID>>";
//    String TRACK_ENDPOINT = "https://ctrl.email.us-ashburn-1.oci.oraclecloud.com";
}
