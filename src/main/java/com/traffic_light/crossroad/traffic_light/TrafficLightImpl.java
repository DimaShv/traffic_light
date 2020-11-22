package com.traffic_light.crossroad.traffic_light;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
@RequiredArgsConstructor
public class TrafficLightImpl implements TrafficLight {
    private Direction allowedDirection = Direction.X;
    private LocalDateTime startTime = LocalDateTime.now();

    public synchronized TrafficLightState getTrafficLightState() {
        return new TrafficLightState(allowedDirection, startTime);
    }

    @Scheduled(fixedDelay = 20000)
    private synchronized void changeDirection() {
        if (this.allowedDirection == Direction.X) {
            this.allowedDirection = Direction.Y;
        } else {
            this.allowedDirection = Direction.X;
        }
        this.startTime = LocalDateTime.now();
        log.info("Traffic light have changed: " + this.allowedDirection);
    }
}
