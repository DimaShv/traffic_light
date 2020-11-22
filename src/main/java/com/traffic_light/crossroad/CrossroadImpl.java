package com.traffic_light.crossroad;

import com.traffic_light.car.Car;
import com.traffic_light.config.Randomizer;
import com.traffic_light.crossroad.traffic_light.Direction;
import com.traffic_light.crossroad.traffic_light.TrafficLight;
import com.traffic_light.crossroad.log.CrossroadLog;
import com.traffic_light.crossroad.log.CrossroadLogRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Log4j2
@Component
public class CrossroadImpl implements Crossroad {
    private final Randomizer randomizer;
    private final TrafficLight trafficLight;
    private final CrossroadLogRepository crossRoadLogRepository;
    private final Queue<Car> xDirectionQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Car> yDirectionQueue = new ConcurrentLinkedQueue<>();

    public CrossroadImpl(TrafficLight trafficLight, CrossroadLogRepository crossRoadLogRepository, Randomizer randomizer) {
        this.trafficLight = trafficLight;
        this.randomizer = randomizer;
        this.crossRoadLogRepository = crossRoadLogRepository;
    }

    private synchronized void crossTheRoad(final boolean isTrafficOffender) {
        Car car;
        Direction greenLightDirection = trafficLight.getTrafficLightState().getAllowedDirection();
        if ((greenLightDirection == Direction.X && !isTrafficOffender)
                ||
            (greenLightDirection == Direction.Y && isTrafficOffender)) {
            car = xDirectionQueue.poll();
        } else {
            car = yDirectionQueue.poll();
        }
        if (car != null) {
            CrossroadLog crossRoadLog = new CrossroadLog(car.getId(),
                    LocalDateTime.now(),
                    isTrafficOffender,
                    car.getDirection());
            crossRoadLogRepository.save(crossRoadLog);
            log.info("The car crossed the road on green light: " + !isTrafficOffender);
        }
    }

    @Override
    public synchronized CrossroadSituation getCrossroadCurrentSituation() {
        CrossroadSituation crossroadSituation = new CrossroadSituation();
        crossroadSituation.setAmountXRoadQueue(xDirectionQueue.size());
        crossroadSituation.setAmountYRoadQueue(yDirectionQueue.size());
        crossroadSituation.setTrafficLightState(trafficLight.getTrafficLightState());
        crossroadSituation.setGeneratedAt(LocalDateTime.now());
        return crossroadSituation;
    }

    public void passTheTrafficLight(Car car) {
        if (Direction.X == car.getDirection()) {
            xDirectionQueue.add(car);
            log.info("xDirection size: {}", xDirectionQueue.size());
        } else {
            yDirectionQueue.add(car);
            log.info("yDirection size: {}", yDirectionQueue.size());
        }
    }

    @Async
    public void startNormalTraffic() {
        while (true) {
            crossTheRoad(false);
        }
    }

    @Async
    public void startRandomTrafficOffenders() throws InterruptedException {
        while (true) {
            Thread.sleep(randomizer.getRandomInt(6000, 9000));
            crossTheRoad(true);
        }
    }
}
