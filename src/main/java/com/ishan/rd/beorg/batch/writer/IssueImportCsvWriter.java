package com.ishan.rd.beorg.batch.writer;

import com.ishan.rd.beorg.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class IssueImportCsvWriter extends CsvFileGenericWriter {
    private static final String[] COLUMN_NAMES = new String[]{"name", "speciality"};


    public IssueImportCsvWriter(Environment environment) {
        super();
    }

    @Autowired
    public IssueImportCsvWriter() {
        super (COLUMN_NAMES, Constants.DEFAULT_CSV_FIELDS_DELIMITER);
    }

   /* @Autowired
    public IssueImportCsvWriter(Environment environment) {
        super(environment, Constants.WHOLESALE_REPORT_FILENAME, COLUMN_NAMES, Constants.DEFAULT_CSV_FIELDS_DELIMITER);
    }*/
}
