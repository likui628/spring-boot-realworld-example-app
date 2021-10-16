package com.example.realworld.api.exception;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ErrorResourceSerializer extends JsonSerializer<ErrorResource> {

    @Override
    public void serialize(ErrorResource resource, JsonGenerator gen, SerializerProvider serial) throws IOException {
        gen.writeStartArray();
        gen.writeString("//todo");
        gen.writeEndArray();
    }

}
