package com.henrixlt.visma2022internship.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.henrixlt.visma2022internship.entity.Meeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class FileReader {

    private static final Path PATH = (Paths.get("src/main/resources/data/test.json")).toAbsolutePath();

    private ObjectMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build()
            .registerModule(new JavaTimeModule());

    public List<Meeting> readAll() throws  IOException {
        TypeReference<List<Meeting>> mapType = new TypeReference<List<Meeting>>() {};
        return mapper.readValue(PATH.toFile(),mapType);
    }

    public void writeAll(List<Meeting> meetings) throws IOException {
        mapper.writeValue(PATH.toFile(),meetings);

    }
}
