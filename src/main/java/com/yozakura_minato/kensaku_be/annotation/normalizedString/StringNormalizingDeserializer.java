package com.yozakura_minato.kensaku_be.annotation.normalizedString;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public final class StringNormalizingDeserializer extends ValueDeserializer<String> {

    /**
     *
     * @param p Parser used for reading JSON content
     * @param ctxt Context that can be used to access information about this deserialization activity.
     * @return {@code null} if the plain string is null or contains only spaces, else returns {@code stripped value}.
     */
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String plainValue = p.getString();
        if (plainValue == null) return null;
        String strippedValue = plainValue.strip();
        return strippedValue.isEmpty() ? null : strippedValue;
    }

}
