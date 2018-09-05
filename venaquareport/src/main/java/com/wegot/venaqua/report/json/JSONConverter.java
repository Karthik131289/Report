package com.wegot.venaqua.report.json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONConverter {

    public static <T> T CovertJSONToObject(File file, Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue( file, cls);
    }
}
