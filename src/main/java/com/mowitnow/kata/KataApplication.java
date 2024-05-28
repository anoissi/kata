package com.mowitnow.kata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {

    @Value("${output.file.path}")
    private String outputFilePath;

    public static void main(String[] args) {
        SpringApplication.run(KataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        resetOutputFile();
    }

    private void resetOutputFile() throws IOException {
        Files.deleteIfExists(Paths.get(outputFilePath));
        Files.createFile(Paths.get(outputFilePath));
    }
}
