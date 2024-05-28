package com.mowitnow.kata.domain.service;

import com.mowitnow.kata.domain.model.Lawn;
import com.mowitnow.kata.domain.model.Mower;

public class MowerService {

    public void processCommands(Mower mower, Lawn lawn, String commands) {
        for (char command : commands.toCharArray()) {
            mower.execute(command, lawn);
        }
    }
}