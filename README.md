# Spring boot keycloak example

This example application will show you how you can protect your Spring Boot application with Keycloak.

## Setting up your Keycloak
Setting up your own Keycloak application is easy.
Either install the right docker image or download the executable and run it on your preferred server.
The only hard step is to enable https. The easiest thing to do is to use nginx and creating the right certificates using let's encrypt.

If you're not going to create your own Keycloak, just use the one provided by switchfully at https://Keycloak.switchfully.com

### Keycloak configuration
Next up we need to configure our Keycloak.

---
First either select or create a new realm. A security realm is how Keycloak groups things together.
Realms are mutually exclusive, so you cannot access users from one realm in another realm.
---
Then select or create a client. A client is the representation of your application in Keycloak.
A few things need to be configured here:
- The access type: confidential/public
  - public means you don't need a client secret, confidential means you do.
- Valid Redirect URIs: the allowed url(s) of your backend application. Put * here to allow everything.
- Web Origins: the allowed url(s) of your frontend application. Put * here to allow everything.
- Roles: the roles you are going to use in your application
---
Lastly create one or more users in the Users menu.
- Go to Role Mapping -> Client Roles and add the appropriate roles for your users
- Enable your user by either selecting User Enabled or Resetting their password.

## Setting up your Spring Boot application

### Keycloak dependencies
At the following dependency to your spring-boot project

```xml
<dependency>
    <groupId>org.keycloak</groupId>
    <artifactId>keycloak-spring-boot-starter</artifactId>
    <version>13.0.1</version>
</dependency>
```
Now add the following properties to your project
```properties
keycloak.auth-server-url=<keycloak_url>auth/
keycloak.realm=<realm_name>
keycloak.resource=<client_name>
keycloak.credentials.secret=<client_secret_if_confidential_client>
keycloak.ssl-required=external
```
