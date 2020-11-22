package com.traffic_light.reports;

import java.time.LocalDateTime;

public interface ReportService {
    TrafficOffendersReportDto getTrafficOffendersReport(LocalDateTime start, LocalDateTime end);

    CrossroadSituationReportDto getCrossroadSituationReport();
}
