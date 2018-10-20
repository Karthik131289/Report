package com.wegot.venaqua.report.ws.handler;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;
import java.util.TreeSet;

public class SoapHandlerImpl implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        TreeSet<QName> headers = new TreeSet();

        return headers;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean response= ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

        if (response) {
            //this is underlying http response object
            HttpServletResponse httpServletResponse = (HttpServletResponse) context.get(MessageContext.SERVLET_RESPONSE);

            //add your desired header
            httpServletResponse.addHeader("Transfer-Encoding", "chunked");
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Server : connection close()......");
    }
}
