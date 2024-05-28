package com.mowitnow.kata.infrastructure.batch;

import com.mowitnow.kata.domain.model.Lawn;
import com.mowitnow.kata.domain.model.Mower;
import com.mowitnow.kata.domain.service.MowerService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MowerItemProcessor implements ItemProcessor<Mower, Mower> {

    private final MowerService mowerService;

    private final LawnStepListener lawnStepListener;

    public MowerItemProcessor(MowerService mowerService, LawnStepListener lawnStepListener) {
        this.mowerService = mowerService;
        this.lawnStepListener = lawnStepListener;
    }

    @Override
    public Mower process(Mower mower) {
        String commands = lawnStepListener.getCommandsForMower();
        Lawn lawn = lawnStepListener.getLawn();
        mowerService.processCommands(mower, lawn, commands);
        return mower;
    }
}
