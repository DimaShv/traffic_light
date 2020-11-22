package com.traffic_light.car;

import com.traffic_light.config.Randomizer;
import com.traffic_light.crossroad.traffic_light.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SimpleCarFactory implements CarFactory {
    private final Randomizer random;

    @Override
    public Car getRandomDirectionCar() {
        return random.getRandomBool() ? new Car(UUID.randomUUID(), Direction.X) : new Car(UUID.randomUUID(), Direction.Y);
    }
}
