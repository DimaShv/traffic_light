package com.traffic_light.reports;

import com.traffic_light.crossroad.log.CrossroadLog;
import com.traffic_light.crossroad.log.CrossroadLogDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TrafficOffendersReportDto {
    private int amountTrafficOffenders;
    private LocalDateTime generatedAt;
    private LocalDateTime from;
    private LocalDateTime to;
    private List<CrossroadLogDto> offenders;

    public TrafficOffendersReportDto(List<CrossroadLog> logs, LocalDateTime from, LocalDateTime to, LocalDateTime generatedAt) {
        this.offenders = logs.stream().map(CrossroadLogDto::new).collect(Collectors.toList());
        this.amountTrafficOffenders = offenders.size();
        this.generatedAt = generatedAt;
        this.from = from;
        this.to = to;
    }
}
