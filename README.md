# Calculator Service

Simple service to evaluate expressions. It is dependent on rest-utils and contains simple authn on some endpoints.

# Local Build Tips

## Build

    cd calculator
    mvn clean install

## Run server

    cd calculator
    mvn exec:java

## Run tests

    cd calculator
    mvn test

# Swagger Endpoint

Open a browser and input the following endpoint:

    http://localhost:8080/openapi/swagger-ui/index.html

## Endpoints

Server is run on PORT 8080. There are 2 main endpoints

### Evaluate
    /v1/calculator/evaluate/{operator}?firstOperand={v1}&secondOperand={v2}

    operator can be the following: add, subtract, multiply, divide

### Audit
    /v1/calculator/audit
    
    endpoint is protected by basic authn


    
