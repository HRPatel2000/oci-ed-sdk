#!/bin/sh

mvn install:install-file -Dfile=oci-java-sdk-email-3.2.4-preview1-SNAPSHOT.jar -DgroupId=com.oracle.oci.sdk -DartifactId=oci-java-sdk-email -Dversion=3.2.4-preview1-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=oci-java-sdk-common-httpclient-jersey-3.2.4-preview1-SNAPSHOT.jar -DgroupId=com.oracle.oci.sdk -DartifactId=oci-java-sdk-common-httpclient-jersey -Dversion=3.2.4-preview1-SNAPSHOT -Dpackaging=jar
