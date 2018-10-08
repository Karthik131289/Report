package com.wegot.venaqua.report.util;

import com.wegot.venaqua.report.ws.exception.ReportException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLUtils {

    public static Unmarshaller getUnMarshaller(Class cls) throws ReportException {
        try {
            JAXBContext jaxbContext = jaxbContext = JAXBContext.newInstance(cls);
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new ReportException(e);
        }
    }

    public static Marshaller getMarshaller(Class cls) throws ReportException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(cls);
            return jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            throw new ReportException(e);
        }
    }

    public static <T> T parseXML(Unmarshaller unmarshaller, InputStream stream) throws ReportException {
        try {
            //new File("D:\\Development\\WegotProject\\Report\\WorkingDir\\Report\\venaquareport\\src\\main\\webapp\\WEB-INF\\classes\\resources\\data-sources.xml")
            return (T) unmarshaller.unmarshal(stream);
        } catch (JAXBException e) {
            throw new ReportException(e);
        }
    }

    public static <T> void serializeXML(Marshaller marshaller, T obj , OutputStream stream) throws ReportException {
        try {
            marshaller.marshal(obj, stream);
        } catch (JAXBException e) {
            throw new ReportException(e);
        }
    }
}
