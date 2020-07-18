package com.ishan.rd.beorg.batch.reader;

import com.ishan.rd.beorg.entity.IssueImportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 */
public class IssueCsvFileReader extends CsvFileGenericReader{

    private static final String PROPERTY_CSV_SOURCE_FILE_PATH = "csv.to.database.job.source.file.path";
    private static final String[] COLUMN_NAMES = new String[]{"email", "fname", "lname", "phone"};

    @Autowired
    public IssueCsvFileReader(Environment environment, String fileName) {
        super(IssueImportDTO.class, environment.getRequiredProperty(PROPERTY_CSV_SOURCE_FILE_PATH).concat(fileName), COLUMN_NAMES, ",", 0);
    }

    @Autowired
    public IssueCsvFileReader(String filePath) {
        super(IssueImportDTO.class, filePath, COLUMN_NAMES, ",", 0);
    }
}
