package com.traffic_light.reports;

import com.traffic_light.crossroad.traffic_light.Direction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CrossroadSituationReportDto {
    private long amountTrafficOffendersCrosses;
    private long amountNormalCrosses;
    private long amountTotalCrosses;
    private long amountXRoadQueue;
    private long amountYRoadQueue;
    private LocalDateTime generatedAt;
    private Direction greenLightDirection;
    private LocalDateTime startGreenLight;
}
