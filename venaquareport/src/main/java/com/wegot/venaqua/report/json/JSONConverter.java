package com.wegot.venaqua.report.json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class JSONConverter {

    public static <T> T CovertToObject(URL url, Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue( url, cls);
    }

    public static <T> T CovertToObject(String jsonData, Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue( jsonData, cls);
    }

    public static <T> String CovertToJsonAsString(T obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> void CovertToJson(File file, T obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, obj);
    }

    public static <T> void CovertToJson(OutputStream stream, T obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(stream, obj);
    }
}
