package com.traffic_light.crossroad.log;

import com.traffic_light.crossroad.traffic_light.Direction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter
public class CrossroadLogDto {
    private Long id;
    private UUID carId;
    private LocalDateTime time;
    private Boolean isTrafficOffender;
    private Direction direction;

    public CrossroadLogDto(CrossroadLog crossroadLog) {
        this.id = crossroadLog.getId();
        this.carId = crossroadLog.getCarId();
        this.time = crossroadLog.getTime();
        this.isTrafficOffender = crossroadLog.getIsTrafficOffender();
        this.direction = crossroadLog.getDirection();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || this.id == null) return false;
        CrossroadLogDto that = (CrossroadLogDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
