# oci-ed-sdk

## Goal

Provide Comprehensive Examples for all OCI Email Delivery Control Plane and Data Plane APIs for major Programming Languages such as Java, Python, Go, Ruby etc. for use in client applications as well as from OCI Functions

## Prerequisites

- OCI Cloud Account
  - Setup Email Delivery Service Resources,
    - Create Email Domain
    - Create Approved Sender
    - Enable Accepted and Relayed Logs on your Email Domain


- Setup OCI Authentication
  - OCI CLI requires active OCI Cloud account. Please visit OCI Cloud Website to signup if you haven't yet.
  - OCI CLI also requires OCI Cloud Authentication setup on your local workstation. You can follow the CLI Setup Document section Setting up the Config File to do that
  - Config File with tenancy id, user id, fingerprint, key file and region can be also found on your OCI Console under Profile → User Section on the Top Right corner. Image below shows the option "View Configuration file" on the OCI Console to copy a file and save on your workstation as ~/.oci/config file
  - Refer this document to get the OCIDs and Keys from OCI Console - https://docs.oracle.com/en-us/iaas/Content/API/Concepts/apisigningkey.htm#five
  - Save the API KEY (private key file) under ~/.oci/
  - Change the path below to point to this private key file in your ~/.oci/config
```shell
key_file=~/.oci/oci_api_key.pem
```
- This completes the Authentication Setup and your OCI CLI is ready to use Http Send API feature


## Setup

Install Required OCI Email Preview SDK Libraries/JARs
- If you are Oracle Internal Team and within Oracle Network, Just take the /lib/settings.xml and either replace it to your ~/.m2/settings.xml or take the internal SNAPSHOT repository from it and put into your ~/.m2/settings.xml
- If you are not Oracle Internal Team, you need to get oci-java-sdk-email-3.2.4-preview1-SNAPSHOT.jar from your Account Manager and then copy it inside /lib directory and then run the commands below

```shell
cd lib/
./ociSDKInstall.sh
```

## Configuration

Instructions
- Clone the project
- Go to the Feature project you would like to reference. Below is the list of supported for now,
  - track/ (For Opens & Clicks Tracking Examples)
  - submit/ (For Http Send API Examples)
- Change your OCI REGION, ENDPOINT, COMPARTMENT_ID and Other Config in OCITenancyConfig.java

## Build and Test

- Build the Project
```java
mvn clean package
```
- This would run various Unit Tests for the feature   

## Use this Features from OCI Java Function

- Prereqiusite - Working OCI Function with proper permissions and policies
- Deploy this java code to your OCI Function and then invoke it
- You can use the code from this project or just use the project to create the New OCI Function