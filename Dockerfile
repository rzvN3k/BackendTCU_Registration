#Load base image containing Java runtime
FROM openjdk:17-slim as build

#Who is the maintainer of the image
MAINTAINER rzvN3k

#keep this for now to give full permissions 
USER root

WORKDIR $JAVA_HOME/jre/lib/security

#copy the certificate in order to import to the keystore
COPY src/main/resources/certs/localhost.p12 $JAVA_HOME/jre/lib/security

#copy the rootCA.crt in order to import the trustore
COPY src/main/resources/certs/rootCA.crt $JAVA_HOME/jre/lib/security


#make sure that packages are all up to date
RUN apt-get update


#import the server certificate
RUN \
    cd $JAVA_HOME/jre/lib/security \
    && keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS -srcstorepass tcubackend -deststorepass serverbackendcert \
    && keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks -storepass tcubackendtrust
    
    
#get back from the WORKDIR
WORKDIR /

#Add the executable
COPY target/registration-0.0.1-SNAPSHOT.jar registration-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar", "/registration-0.0.1-SNAPSHOT.jar"]



