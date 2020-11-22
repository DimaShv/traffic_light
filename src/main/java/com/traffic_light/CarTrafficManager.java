package com.traffic_light;

import com.traffic_light.car.CarFactory;
import com.traffic_light.config.Randomizer;
import com.traffic_light.crossroad.Crossroad;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CarTrafficManager {
    private final Crossroad crossroad;
    private final CarFactory carFactory;
    private final Randomizer randomizer;

    @Async
    public void pushToTheCrossroadGeneratedCar() throws InterruptedException {
        while (true) {
            Thread.sleep(randomizer.getRandomInt(1, 5000));
            crossroad.passTheTrafficLight(carFactory.getRandomDirectionCar());
        }
    }
}
