package com.epam.lab.exporter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import java.io.File;

public class JaxBExporter implements EntityExporter {
	final static Logger LOG = Logger.getLogger(JaxBExporter.class);

	@Override
	public boolean export(Object exportClass, String exportToFilename) {
		LOG.trace("Start JAXB exporting");
		File file = new File(exportToFilename);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(exportClass.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(exportClass, file);
			jaxbMarshaller.marshal(exportClass, System.out);
		} catch (JAXBException e) {
			LOG.error("Unable to create XML file from object. ({})");
			return false;
		}
		return true;
	}
}
