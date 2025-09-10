package com.tco.misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tco.requests.ConfigRequest;

public final class JSONReader {

    private static final transient Logger log = LoggerFactory.getLogger(JSONReader.class);

    private JSONReader() {
    }

    public static String fetchValidatedJSONFile(String filePath, Class clazz) throws InternalRequestException {
        String aboutString;
        try {
            InputStream aboutInputStream = ConfigRequest.class.getResourceAsStream(filePath);
            Objects.requireNonNull(aboutInputStream);
            aboutString = new String(aboutInputStream.readAllBytes());
            JSONValidator.validate(aboutString, clazz);
        } catch (IOException e) {
            log.error("Bad Format - {}", e.getMessage());
            throw new InternalRequestException();
        } catch (NullPointerException e) {
            log.error("File Not Found - {}", filePath);
            throw new InternalRequestException();
        }
        return aboutString;
    }

}
