package br.com.gpimanager.services;

import br.com.gpimanager.domains.dashboard.DashboardReportDto;
import br.com.gpimanager.domains.dashboard.DashboardReportDtoBuilder;
import br.com.gpimanager.domains.dashboard.DashboardReportRepository;
import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.domains.process.IndustrialProcessRepository;
import br.com.gpimanager.domains.process.ProcessStatus;
import br.com.gpimanager.domains.process.ProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DashboardReportServiceImpl implements DashboardReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardReportServiceImpl.class);

    private final IndustrialProcessRepository industrialProcessRepository;
    private final DashboardReportRepository dashboardReportRepository;

    private final List<Runnable> tasks;

    public DashboardReportServiceImpl(IndustrialProcessRepository industrialProcessRepository,
                                      DashboardReportRepository dashboardReportRepository) {
        this.industrialProcessRepository = industrialProcessRepository;
        this.dashboardReportRepository = dashboardReportRepository;

        tasks = Collections.singletonList(() -> {
            this.loadProcessTypeNewOrderDashboardReport();
            this.loadProcessTypeDeliveryTissueDashboardReport();
        });
    }

    @Override
    public void initializeCache() {
        LOGGER.info("Loading Dashboard Report Cache...");

        long start = System.currentTimeMillis();

        this.dashboardReportRepository.clearCache();

        this.executeAllTasks(tasks).join();

        LOGGER.info("Dashboard Report Cache loaded in {} ms", System.currentTimeMillis() - start);

        LOGGER.info("Load {} Dashboard Report Cache", this.dashboardReportRepository.size());
    }

    @Override
    public void updateCacheDashboarReport(IndustrialProcessDto process) {
        dashboardReportRepository.update(process);
    }

    @Override
    public ConcurrentMap<Integer, DashboardReportDto> getDashboardReport() {
        return dashboardReportRepository.getAllDashboardReports();
    }

    public void loadProcessTypeNewOrderDashboardReport() {
        addDashboardReportToCache(ProcessType.NEW_ORDER);
    }

    public void loadProcessTypeDeliveryTissueDashboardReport() {
        addDashboardReportToCache(ProcessType.DELIVERY_TISSUE);
    }

    private void addDashboardReportToCache(ProcessType processType) {
        DashboardReportDtoBuilder builder = new DashboardReportDtoBuilder();

        DashboardReportDto report = builder
                .setInitiatedQuantity(industrialProcessRepository.countByProcessTypeAndProcessStatus(processType, ProcessStatus.INITIATED))
                .setProcessingQuantity(industrialProcessRepository.countByProcessTypeAndProcessStatus(processType, ProcessStatus.PROCESSING))
                .setSuccessQuantity(industrialProcessRepository.countByProcessTypeAndProcessStatus(processType, ProcessStatus.SUCCESS))
                .setOverdueQuantity(industrialProcessRepository.countByProcessTypeAndProcessStatus(processType, ProcessStatus.OVERDUE))
                .setFailedQuantity(industrialProcessRepository.countByProcessTypeAndProcessStatus(processType, ProcessStatus.FAILED))
                .build();

        this.dashboardReportRepository.add(processType, report);
    }

    private CompletableFuture<Void> executeAllTasks(List<Runnable> tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executor)).toArray(CompletableFuture[]::new));
    }

}
