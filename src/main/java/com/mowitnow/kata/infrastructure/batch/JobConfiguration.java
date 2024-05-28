package com.mowitnow.kata.infrastructure.batch;

import com.mowitnow.kata.domain.model.Mower;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

    @Bean
    public Job mowJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("mowJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(
            PlatformTransactionManager transactionManager,
            JobRepository jobRepository,
            LawnStepListener lawnStepListener,
            MowerItemReader mowerItemReader,
            MowerItemProcessor mowerItemProcessor,
            MowerItemWriter mowerItemWriter) {
        return new StepBuilder("step1", jobRepository)
                .<Mower, Mower>chunk(1, transactionManager)
                .reader(mowerItemReader)
                .processor(mowerItemProcessor)
                .writer(mowerItemWriter)
                .listener(lawnStepListener)
                .build();
    }
}
