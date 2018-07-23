package com.epam.lab.importer;

import java.io.InputStream;
import java.util.List;

public interface Importer {
	@SuppressWarnings("rawtypes")
	List parse(InputStream is);
}
