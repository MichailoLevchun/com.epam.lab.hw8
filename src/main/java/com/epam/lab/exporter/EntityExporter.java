package com.epam.lab.exporter;

public interface EntityExporter {
    boolean export(Object exportClass, String exportToFilename);
}
