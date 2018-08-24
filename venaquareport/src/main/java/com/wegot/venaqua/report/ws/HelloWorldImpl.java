package com.wegot.venaqua.report.ws;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.wegot.venaqua.report.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }

/*    <!--<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
         "http://java.sun.com/dtd/web-app_2_3.dtd" >-->
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>VenAqua Report</display-name>
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  <listener>
    <listener-class>com.sun.xml.ws.transport.http.servlet.WSServletContextListener</listener-class>
  </listener>
  <servlet>
    <servlet-name>venaqua-report</servlet-name>
    <servlet-class>com.sun.xml.ws.transport.http.servlet.WSServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>venaqua-report</servlet-name>
    <url-pattern>/report</url-pattern>
  </servlet-mapping>
</web-app>

    <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE web-app PUBLIC "-//Sun Microsystems,
    Inc.//DTD Web Application 2.3//EN"
                "http://java.sun.com/j2ee/dtds/web-app_2_3.dtd">*/
}
