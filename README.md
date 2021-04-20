# JL-ProductCatalogueReduceApi

A Restful webservice written using SpringBoot which returns the list of dresses that have a price reduction

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.

* Clone this repository 
* Make sure you are using JDK 1.8
* You can build the project and run the tests by running ```gradlew clean build```
* Once successfully built, you can run the service by one of these two methods:

```
      Execute the `main` method in the `com.jl.product.ProductApplication` class from your IDE.

or
       gradle bootRun
```



