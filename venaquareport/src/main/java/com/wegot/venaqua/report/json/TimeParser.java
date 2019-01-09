package com.wegot.venaqua.report.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeParser extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try {
            return formatter.parse(text);
        } catch (ParseException e) {
            throw new IOException(e.getMessage());
        }
    }
}
