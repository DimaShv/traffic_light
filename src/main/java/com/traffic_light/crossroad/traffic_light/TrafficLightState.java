package com.traffic_light.crossroad.traffic_light;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TrafficLightState {
    private final Direction allowedDirection;
    private final LocalDateTime startTime;
}
