package com.mowitnow.kata.infrastructure.batch;

import com.mowitnow.kata.domain.model.Lawn;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class LawnStepListener implements StepExecutionListener {

    private Lawn lawn;
    private List<String> lines;
    private int currentCommandIndex = 2;

    @Value("${input.file.path}")
    private String inputFilePath;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            lines = reader.lines().toList();
            String[] lawnDimensions = lines.get(0).split(" ");
            lawn = new Lawn(Integer.parseInt(lawnDimensions[0]), Integer.parseInt(lawnDimensions[1]));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the input file", e);
        }
    }

    public Lawn getLawn() {
        return lawn;
    }

    public String getCommandsForMower() {
        if (currentCommandIndex < lines.size()) {
            String commands = lines.get(currentCommandIndex);
            currentCommandIndex += 2;
            return commands;
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}

