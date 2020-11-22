package com.traffic_light.config;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Randomizer {
    private static final Random RANDOM = new Random();

    public boolean getRandomBool() {
        return RANDOM.nextBoolean();
    }

    public int getRandomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
