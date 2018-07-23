package com.epam.lab.importer;

import com.epam.lab.entity.Aircraft;
import com.epam.lab.entity.Plane;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AircraftSTAXParser implements Importer {
	final static Logger LOG = Logger.getLogger(AircraftSTAXParser.class);

	private StringBuilder accumulator;
	private List<Aircraft> aircrafts;
	private Aircraft aircraft;

	public AircraftSTAXParser() {
		accumulator = new StringBuilder();
		aircrafts = new ArrayList<>();
		LOG.trace("Init STAXParser complete");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List parse(InputStream is) {
		String elementName;
		XMLInputFactory factory = XMLInputFactory.newFactory();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while (reader.hasNext()) {
				int next = reader.next();
				switch (next) {
				case XMLStreamConstants.START_ELEMENT:
					elementName = reader.getLocalName();
					accumulator.setLength(0);
					if ("plane".equals(elementName)) {
						aircraft = new Plane();
						LOG.trace("New plane element founded.");
					}
					break;

				case XMLStreamConstants.END_ELEMENT:
					elementName = reader.getLocalName();
					if ("plane".equals(elementName)) {
						aircrafts.add(aircraft);
						LOG.trace("Plane element added to list");
					}
					saveTagToModel(elementName, accumulator.toString());
					break;
				case XMLStreamConstants.CHARACTERS:
					accumulator.append(reader.getText());
					break;
				case XMLStreamConstants.END_DOCUMENT:
					LOG.trace("STAXParsing done");
					break;
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return aircrafts;
	}

	private void saveTagToModel(String tagName, String value) {
		switch (tagName) {
		case "model":
			aircraft.setModel(value);
			break;
		case "origin":
			aircraft.setOrigin(value);
			break;
		case "type":
			aircraft.setType(value);
			break;
		case "seats":
			aircraft.setSeats(Integer.parseInt(value));
			break;
		case "weapons":
			aircraft.setWeapons(Boolean.parseBoolean(value));
			break;
		case "missiles":
			aircraft.setMissiles(Integer.parseInt(value));
			break;
		case "hasRadar":
			aircraft.setHasRadar(Boolean.parseBoolean(value));
			break;
		case "length":
			aircraft.setLength(Double.parseDouble(value));
			break;
		case "width":
			aircraft.setWidth(Double.parseDouble(value));
			break;
		case "height":
			aircraft.setHeight(Double.parseDouble(value));
			break;
		case "amount":
			aircraft.setAmount(Double.parseDouble(value));
			break;
		case "currency":
			aircraft.setCurrency(value);
			break;
		}
	}
}
