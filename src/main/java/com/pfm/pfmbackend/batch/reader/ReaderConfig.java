package com.pfm.pfmbackend.batch.reader;

import com.pfm.pfmbackend.batch.dto.TransactionCsvDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<TransactionCsvDTO> transactionItemReader() {
        return new FlatFileItemReaderBuilder<TransactionCsvDTO>()
                .name("transactionItemReader")
                .resource(new FileSystemResource("D:/PFM/transactions1.csv")) // ✅ fichier modifiable sans redémarrer
                .delimited()
                .names("bhLib", "dco", "mon", "sen", "iban")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(TransactionCsvDTO.class);
                }})
                .linesToSkip(1)
                .build();
    }
}
