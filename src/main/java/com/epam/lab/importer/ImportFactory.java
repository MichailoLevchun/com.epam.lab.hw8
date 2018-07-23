package com.epam.lab.importer;


import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

public class ImportFactory {
	final static Logger LOG = Logger.getLogger(ImportFactory.class);

	@SuppressWarnings("rawtypes")
	public static final List importFromXML(InputStream is, Class clazz) {
		Importer importer;
		try {
			Class c = Class.forName(clazz.getName());
			importer = (Importer) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			LOG.error("Wrong parser name {}." + clazz.getSimpleName());
			throw new RuntimeException("Unable to create parser instance.");
		}
		return importer.parse(is);
	}
}
