package com.epam.lab.importer;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.epam.lab.importer.handler.SAXHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AircraftSAXParser implements Importer {
	final static Logger LOG = Logger.getLogger(AircraftSAXParser.class);

	@SuppressWarnings("rawtypes")
	@Override
	public List parse(InputStream is) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		SAXHandler handler = new SAXHandler();
		try {
			parser = factory.newSAXParser();
			parser.parse(is, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOG.error("Unable to parse XML file");
			throw new RuntimeException("Unable to parse file");
		}
		return handler.returnList();
	}
}
