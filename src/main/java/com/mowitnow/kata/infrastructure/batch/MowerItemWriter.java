package com.mowitnow.kata.infrastructure.batch;

import com.mowitnow.kata.domain.model.Mower;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Component
public class MowerItemWriter implements ItemWriter<Mower> {

    @Value("${output.file.path}")
    private String outputFilePath;

    @Override
    public void write(Chunk<? extends Mower> chunk) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            for (Mower mower : chunk) {
                writer.write(mower.getPosition().getX() + " " +
                        mower.getPosition().getY() + " " +
                        mower.getDirection());
                writer.newLine();
            }
        }
    }
}

