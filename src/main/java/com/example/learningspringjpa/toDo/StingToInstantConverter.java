package com.example.learningspringjpa.toDo;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class StingToInstantConverter implements Converter<String, Instant> {
    @Override
    public Instant convert(String source) {
        return LocalDate.parse(source)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);
    }
}
