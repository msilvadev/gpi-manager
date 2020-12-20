package br.com.gpimanager.controllers;

import br.com.gpimanager.domains.dashboard.DashboardReportDto;
import br.com.gpimanager.services.DashboardReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("dashboard-report")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardReportController {

    private final DashboardReportService dashboardReportService;

    public DashboardReportController(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @GetMapping
    public ResponseEntity<ConcurrentMap<Integer, DashboardReportDto>> getDashboardReport() {
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }

    @PostMapping("refresh-cache")
    public ResponseEntity<ConcurrentMap<Integer, DashboardReportDto>> refreshCache() {
        dashboardReportService.initializeCache();
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }
}
