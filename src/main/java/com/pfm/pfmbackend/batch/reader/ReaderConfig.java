package com.pfm.pfmbackend.batch.reader;

import com.pfm.pfmbackend.batch.dto.TransactionCsvDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ReaderConfig {
    @Bean
    public FlatFileItemReader<TransactionCsvDTO> transactionItemReader() {
        return new org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder<TransactionCsvDTO>()
                .name("transactionItemReader")
                .resource(new org.springframework.core.io.ClassPathResource("transactions.csv"))
                .delimited()
                .names("bhLib", "dco", "mon", "sen", "iban")
                .fieldSetMapper(new org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper<>() {{
                    setTargetType(TransactionCsvDTO.class);
                }})
                .linesToSkip(1)
                .build();
    }

}
