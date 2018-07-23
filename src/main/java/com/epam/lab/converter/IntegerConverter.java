package com.epam.lab.converter;

public class IntegerConverter implements Converter {
    @Override
    public Object convert(String value) {
        return Integer.parseInt(value.trim());
    }
}
