package com.traffic_light.crossroad.log;

import com.traffic_light.crossroad.traffic_light.Direction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CrossroadLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private UUID carId;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private Boolean isTrafficOffender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    public CrossroadLog(UUID carId, LocalDateTime time, Boolean isTrafficOffender, Direction direction) {
        this.carId = carId;
        this.time = time;
        this.isTrafficOffender = isTrafficOffender;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || this.id == null) return false;
        CrossroadLog that = (CrossroadLog) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

