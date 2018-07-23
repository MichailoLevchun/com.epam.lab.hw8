package com.epam.lab.converter;

public class DoubleConverter implements Converter{
    @Override
    public Object convert(String value) {
        return Double.parseDouble(value);
    }
}
