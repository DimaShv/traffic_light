package com.traffic_light;

import com.traffic_light.crossroad.Crossroad;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
@Log4j2
@RestController
public class TrafficLightApp implements CommandLineRunner {
    private final Crossroad crossroad;
    private final CarTrafficManager carTrafficManager;

    public static void main(String[] args) {
        SpringApplication.run(TrafficLightApp.class, args);
    }

    @Bean
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        return threadPoolTaskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        carTrafficManager.pushToTheCrossroadGeneratedCar();
        crossroad.startNormalTraffic();
        crossroad.startRandomTrafficOffenders();
    }
}
