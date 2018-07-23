package com.epam.lab.converter;

public class StringConverter implements Converter{
    @Override
    public Object convert(String value) {
        return value;
    }
}
