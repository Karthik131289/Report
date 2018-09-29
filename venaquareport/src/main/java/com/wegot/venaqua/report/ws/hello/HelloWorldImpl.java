package com.wegot.venaqua.report.ws.hello;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.wegot.venaqua.report.ws.hello.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
