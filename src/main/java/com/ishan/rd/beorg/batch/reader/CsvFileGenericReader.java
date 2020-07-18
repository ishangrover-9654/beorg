package com.ishan.rd.beorg.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.ClassPathResource;

/**
 * Generic reader class for any flat file it is used for all CSV files read from
 * mainframe
 *
 * @author ishan
 * @param <T>
 */
public class CsvFileGenericReader<T> extends FlatFileItemReader<T> {

    private final Class<T> payloadClass;

    public CsvFileGenericReader(Class<T> payloadClass, String filePath, String[] fieldNames, String delimiter, int linesToSkip) {
        super();
        this.payloadClass = payloadClass;
        this.setResource(new ClassPathResource(filePath));
        this.setLinesToSkip(linesToSkip);
        this.setLineMapper(createLineMapper(fieldNames, delimiter));
    }

    protected final LineMapper<T> createLineMapper(String[] fieldNames, String delimiter) {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        if (fieldNames != null || fieldNames.length > 0) {
            lineMapper.setLineTokenizer(createLineTokenizer(fieldNames, delimiter));
        }
        lineMapper.setFieldSetMapper(this.createInformationMapper());
        return lineMapper;
    }

    protected LineTokenizer createLineTokenizer(String[] fieldNames, String delimiter) {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setNames(fieldNames);
        return lineTokenizer;
    }

    protected FieldSetMapper<T> createInformationMapper() {
        BeanWrapperFieldSetMapper<T> informationMapper = new BeanWrapperFieldSetMapper();
        informationMapper.setTargetType(payloadClass);
        return informationMapper;
    }

    /*@Bean
    public JdbcBatchItemWriter<ReceiverProfile> writer() {
        JdbcBatchItemWriter<ReceiverProfile> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME) VALUES (:id, :firstName, :lastName)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ReceiverProfile>());
        return itemWriter;
    }*/

    /*@Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:employee.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }*/
}