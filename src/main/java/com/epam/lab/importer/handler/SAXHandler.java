package com.epam.lab.importer.handler;

import com.epam.lab.converter.BooleanConverter;
import com.epam.lab.converter.Converter;
import com.epam.lab.converter.DoubleConverter;
import com.epam.lab.converter.IntegerConverter;
import com.epam.lab.entity.Aircraft;
import com.epam.lab.entity.Plane;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SAXHandler extends DefaultHandler {
	final static Logger LOG = Logger.getLogger(SAXHandler.class);
	@SuppressWarnings("rawtypes")
	private static Class scanClass;
	private static Map<String, Class<?>> methodTypes;
	private static Map<String, Method> methods;
	private static Map<String, Converter> converters;
	private StringBuilder accumulator;
	private List<Aircraft> aircrafts;
	private Aircraft aircraft;

	public SAXHandler() {
		scanClassForMethods(Aircraft.class);
		scanClass = Plane.class;
		fillConverters();
		accumulator = new StringBuilder();
		aircrafts = new ArrayList<>();
	}

	@SuppressWarnings("rawtypes")
	public void scanClassForMethods(Class clazz) {
		methodTypes = new HashMap<>();
		methods = new HashMap<>();
		Method[] declaredMethods = clazz.getMethods();
		if (declaredMethods.length > 0) {
			for (Method method : declaredMethods) {
				Class<?>[] types = method.getParameterTypes();
				if (types.length > 0) {
					final Class<?> c = types[0];
					methodTypes.put(method.getName(), c);
					methods.put(method.getName(), method);
				}
			}
		}
	}

	public void fillConverters() {
		converters = new HashMap<>();
		converters.put("int", new IntegerConverter());
		converters.put("double", new DoubleConverter());
		converters.put("boolean", new BooleanConverter());
	}

	@Override
	public void startDocument() throws SAXException {
		LOG.trace("Start SAX parsing XML file.");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		accumulator.setLength(0);
		String className = scanClass.getSimpleName().toLowerCase();
		if (qName.equals(className)) {
			LOG.trace("New class element found {}");
			try {
				aircraft = (Aircraft) scanClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException("Unable to create empty class");
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		LOG.trace("Append new characters {}");
		accumulator.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		fillAircraftClass(qName, accumulator.toString());
		String className = scanClass.getSimpleName().toLowerCase();
		if (qName.equals(className)) {
			LOG.trace("Object plane added to list");
			aircrafts.add(aircraft);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		LOG.trace("SAX parsing done.");
	}

	private void fillAircraftClass(String param, String value) {
		String methodNameCase = param.substring(0, 1).toUpperCase() + param.substring(1);
		String setMethodName = "set" + methodNameCase;
		if (!methodTypes.containsKey(setMethodName))
			return;
		Class<?> fieldType = methodTypes.get(setMethodName);
		String typeClass = fieldType.getSimpleName();
		Method setMethodObject = methods.get(setMethodName);
		try {
			if (converters.containsKey(typeClass)) {
				Converter converter = converters.get(typeClass);
				setMethodObject.invoke(aircraft, converter.convert(value));
			} else {
				setMethodObject.invoke(aircraft, value);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Wrong model param `{}`" + param);
		}
	}

	@SuppressWarnings("rawtypes")
	public List returnList() {
		return aircrafts;
	}
}