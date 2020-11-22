package com.traffic_light.crossroad;

import com.traffic_light.crossroad.traffic_light.TrafficLightState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CrossroadSituation {
    private LocalDateTime generatedAt;
    private TrafficLightState trafficLightState;
    private long amountXRoadQueue;
    private long amountYRoadQueue;
}
