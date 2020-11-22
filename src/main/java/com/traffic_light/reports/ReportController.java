package com.traffic_light.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/api/v1/report/traffic-offenders")
    public ResponseEntity<TrafficOffendersReportDto> getTrafficOffenders(@RequestParam(required = false)
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime from,
                                                                         @RequestParam(required = false)
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime to) {
        return ResponseEntity.ok(reportService.getTrafficOffendersReport(from, to));
    }

    @GetMapping("/api/v1/report/situation")
    public ResponseEntity<CrossroadSituationReportDto> getCurrentSituationReport() {
        return ResponseEntity.ok(reportService.getCrossroadSituationReport());
    }
}
