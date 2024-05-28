package com.mowitnow.kata;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KataApplicationTests {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job mowJob;

    @Test
    void testMowJob_shouldCompleted() throws Exception {
        JobExecution jobExecution = jobLauncher.run(mowJob, new JobParametersBuilder().toJobParameters());

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

    @Test
    void testMowJob_shouldReturnTheExpectedOutFile() throws Exception {
        jobLauncher.run(mowJob, new JobParametersBuilder().toJobParameters());

        Path outputPath = Paths.get("src/test/resources/output.txt");
        Path expectedOutputPath = Paths.get("src/test/resources/expected-output.txt");

        List<String> outputLines = Files.readAllLines(outputPath);
        List<String> expectedOutputLines = Files.readAllLines(expectedOutputPath);

        assertThat(outputLines).containsExactlyElementsOf(expectedOutputLines);
    }
}
