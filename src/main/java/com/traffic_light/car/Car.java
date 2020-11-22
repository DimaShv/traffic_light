package com.traffic_light.car;

import com.traffic_light.crossroad.traffic_light.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class Car {
    private UUID id;
    private Direction direction;
}
