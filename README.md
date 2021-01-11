# delivery-service project

## Helm Install

### Set env variables
```
export CLUSTER_APP_BASE=apps.cluster.example.com
```

### First-time Install
```
helm install delivery-service-v1 --set firstInstall=true --set baseOpenshiftAppHostname=$CLUSTER_APP_BASE .openshift/helm
```

### Deploying a New Version
To deploy another version of an app, you must set version, versionName, and dockerTag. This will create a new workload for the app in which you can control with VirtualServices and DestinationRules. 

The following example deploys version 2 of the delivery-service application.

```
helm install delivery-service-v2 --set version=2.0.0 --set versionName=v2 --set dockerTag=2.0.0 .openshift/helm
```

> __Note:__ Helm versioning was an issue as Service Mesh needed to only create a new deployment; All other resources should be the same! 


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `delivery-service-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/delivery-service-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/delivery-service-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.