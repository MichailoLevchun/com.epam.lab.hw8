package com.epam.lab.controller;

import com.epam.lab.entity.Aircraft;
import com.epam.lab.entity.Planes;
import com.epam.lab.exporter.EntityExporter;
import com.epam.lab.exporter.JaxBExporter;
import com.epam.lab.importer.AircraftDOMParser;
import com.epam.lab.importer.ImportFactory;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

public class Main {
	final static Logger LOG = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.trace("Loading xml file");
		ClassLoader classLoader = Main.class.getClassLoader();
		InputStream xmlStream = classLoader.getResourceAsStream("xml/planes.xml");
		@SuppressWarnings("unchecked")
		List<Aircraft> planeList = ImportFactory.importFromXML(xmlStream, AircraftDOMParser.class);
		if (!planeList.isEmpty()) {
			LOG.trace("Save to XML");
			Planes planesWrapper = new Planes(planeList);
			EntityExporter exporter = new JaxBExporter();
			if (!exporter.export(planesWrapper, "out.xml")) {
				LOG.info("Unable to save data to xml");
			} else {
				LOG.info("Data successfully saved to xml!");
			}
		} else {
			LOG.trace("No returned data");
		}
	}
}
