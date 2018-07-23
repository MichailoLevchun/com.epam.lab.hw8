package com.epam.lab.converter;

public class BooleanConverter implements Converter {
    @Override
    public Object convert(String value) {
        return Boolean.valueOf(value);
    }
}
