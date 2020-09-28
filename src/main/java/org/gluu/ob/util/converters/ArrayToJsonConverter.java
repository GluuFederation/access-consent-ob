package org.gluu.ob.util.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Slf4j
@Converter(autoApply = true)
public class ArrayToJsonConverter implements AttributeConverter<String[], String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[] accounts) {
        try {
            if (accounts != null) {
                return objectMapper.writeValueAsString(accounts);
            }
        } catch (JsonProcessingException ex) {
            log.error("Accounts list can not be converted to Json String.", ex);
        }
        return null;
    }

    @Override
    public String[] convertToEntityAttribute(String accounts) {
        try {
            if (accounts != null) {
                return objectMapper.readValue(accounts, String[].class);
            }
        } catch (IOException ex) {
            log.error("Problems parsing Pin string to object", ex);
        }
        return null;
    }

}
