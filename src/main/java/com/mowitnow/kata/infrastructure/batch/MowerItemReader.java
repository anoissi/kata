package com.mowitnow.kata.infrastructure.batch;

import com.mowitnow.kata.domain.model.Direction;
import com.mowitnow.kata.domain.model.Mower;
import com.mowitnow.kata.domain.model.Position;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Component
public class MowerItemReader implements ItemReader<Mower> {

    @Value("${input.file.path}")
    private String inputFilePath;
    private BufferedReader reader;
    private int lineNumber = 0;
    private List<String> lines;

    @Override
    public Mower read() throws Exception {
        if (reader == null) {
            reader = new BufferedReader(new FileReader(inputFilePath));
            lines = reader.lines().toList();
        }

        if (lineNumber >= lines.size()) {
            return null;
        }

        if (lineNumber % 2 == 1) {
            String[] parts = lines.get(lineNumber).split(" ");
            Position position = new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            Direction direction = Direction.valueOf(parts[2]);
            lineNumber++;
            return new Mower(position, direction);
        }
        lineNumber++;
        return read();
    }
}

