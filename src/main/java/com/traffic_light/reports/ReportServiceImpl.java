package com.traffic_light.reports;

import com.traffic_light.crossroad.Crossroad;
import com.traffic_light.crossroad.CrossroadSituation;
import com.traffic_light.crossroad.log.CrossroadLogRepository;
import com.traffic_light.crossroad.log.CrossroadLogSpecification;
import com.traffic_light.crossroad.traffic_light.TrafficLightState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final CrossroadLogRepository crossroadLogRepository;
    private final Crossroad crossroad;

    @Transactional(readOnly = true)
    public TrafficOffendersReportDto getTrafficOffendersReport(LocalDateTime from, LocalDateTime to) {
        LocalDateTime now = LocalDateTime.now();
        CrossroadLogSpecification specification = new CrossroadLogSpecification();
        specification.setStartPeriod(from);
        specification.setEndPeriod(to);
        specification.setIsTrafficOffender(true);
        return new TrafficOffendersReportDto(crossroadLogRepository.findAll(specification, Sort.by(new Sort.Order(Sort.Direction.DESC, "time"))),
                from, to, now);
    }

    @Override
    public CrossroadSituationReportDto getCrossroadSituationReport() {
        CrossroadSituation crossroadCurrentSituation = crossroad.getCrossroadCurrentSituation();
        TrafficLightState trafficLightState = crossroadCurrentSituation.getTrafficLightState();

        CrossroadLogSpecification specification = new CrossroadLogSpecification();
        specification.setStartPeriod(trafficLightState.getStartTime());
        specification.setEndPeriod(crossroadCurrentSituation.getGeneratedAt());
        specification.setIsTrafficOffender(true);

        long amountTrafficOffendersCrosses = crossroadLogRepository.count(specification);
        specification.setIsTrafficOffender(false);
        long amountNormalCrosses = crossroadLogRepository.count(specification);

        CrossroadSituationReportDto report = new CrossroadSituationReportDto();
        report.setAmountXRoadQueue(crossroadCurrentSituation.getAmountXRoadQueue());
        report.setAmountYRoadQueue(crossroadCurrentSituation.getAmountYRoadQueue());
        report.setGeneratedAt(crossroadCurrentSituation.getGeneratedAt());
        report.setGreenLightDirection(trafficLightState.getAllowedDirection());
        report.setStartGreenLight(trafficLightState.getStartTime());
        report.setAmountNormalCrosses(amountNormalCrosses);
        report.setAmountTrafficOffendersCrosses(amountTrafficOffendersCrosses);
        report.setAmountTotalCrosses(amountNormalCrosses + amountTrafficOffendersCrosses);
        return report;
    }
}
