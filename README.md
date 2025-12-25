# Oracle WebCenter Sites Single Sign On 

`owcs-sso` is a Java library for working with authenticated resources in Oracle WebCenter Sites.

See [Using REST Resources with the WEM Framework](https://docs.oracle.com/en/middleware/webcenter/sites/12.2.1.3/wbcsd/using-rest-resources-wem-framework.html)

![GitHub stars](https://img.shields.io/github/stars/glynch/owcs-sso?style=social)
![GitHub forks](https://img.shields.io/github/forks/glynch/owcs-sso?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/glynch/owcs-sso?style=social)
![GitHub repo size](https://img.shields.io/github/repo-size/glynch/owcs-sso)
![GitHub language count](https://img.shields.io/github/languages/count/glynch/owcs-sso)
![GitHub top language](https://img.shields.io/github/languages/top/glynch/owcs-sso)
![GitHub last commit](https://img.shields.io/github/last-commit/glynch/owcs-sso?color=red)


## Pre Requisites

The following dependencies need to be installed before this project can be built.

See each project for installation information.

[owcs-parent](https://github.com/glynch/owcs-parent)

[owcs-test](https://github.com/glynch/owcs-test)

## Installation

```bash
    ./mvnw clean install
```

## Usage

### Get a Ticket Granting Ticket (TGT)

- `timeToLiveSeconds` is the maximum time that the TGT can be used before it expires.
- `timeToIdleSeconds` is the maximum time that the TGT will remain in the cache without being used.

```java
    TicketGrantingTicketProvider ticketGrantingTicketProvideer = CachingTicketGrantingTicketProvider.create("http://localhost:7003/cas");
    String tgt = ticketGrantingTicketProvider.getTicketGrantingTicket("fwadmin", "xceladmin");
```

> Using the CachingTicketGrantingTicketProvider will cache the TGT so it can be re-used. The default `timeToLiveSeconds` is 8 hours and the `timeToIdleSeconds` is 2 hours. 
 

### Get a Service Ticket (ST)

Service tickets are single use and expire after they are used. 

```java
    TicketProvider ticketProvider = TickerProvider.create("http://localhost:7003/cas");
    String st = ticketProvider.getTicket("http://localhost:7003/sites/REST/sites", "fwadmin", "xceladmin");
```

### Get a Multi Ticket (multi)

```java
    TicketProvider ticketProvider = TickerProvider.create("http://localhost:7003/cas");
    String multiTicket = ticketProvider.getMultiTicket("fwadmin", "xceladmin");
```

### Get an Encrypted Token 

This token can be used as the `X-CSRF_TOKEN` header for request to autneticated REST resources.

- `timeToLiveSeconds` is the maximum time that the token can be used before it expires.

```java
    TokenProvider tokenProvider = CachingTokenProvider.create("http://localhost:7003/cas");
    String token = tokenProvider.getToken("fwadmin", "xceladmin");
```

> The `timeToIdleSeconds` defaults to 900 seconds which is the default `cs.timeout`



