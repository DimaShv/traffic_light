package com.traffic_light.crossroad;

import com.traffic_light.car.Car;

public interface Crossroad {
    CrossroadSituation getCrossroadCurrentSituation();
    void passTheTrafficLight(Car car);
    void startNormalTraffic();
    void startRandomTrafficOffenders() throws InterruptedException;
}
