package br.com.gpimanager.services.dashboard;

import br.com.gpimanager.domains.dashboard.DashboardReportDto;
import br.com.gpimanager.domains.process.IndustrialProcessDto;

import java.util.concurrent.ConcurrentMap;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(IndustrialProcessDto process);

    ConcurrentMap<Integer, DashboardReportDto> getDashboardReport();
}
