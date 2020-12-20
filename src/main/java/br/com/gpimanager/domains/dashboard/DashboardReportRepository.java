package br.com.gpimanager.domains.dashboard;

import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.domains.process.ProcessStatus;
import br.com.gpimanager.domains.process.ProcessType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

@Component
public class DashboardReportRepository {

    private final ConcurrentHashMap<Integer, DashboardReportDto> dashboardCache = new ConcurrentHashMap<>();

    private final Map<Integer, Consumer<DashboardReportDto>> incrementQuantityToProcessStatus = new HashMap<>();

    @PostConstruct
    public void initialize() {
        incrementQuantityToProcessStatus.put(ProcessStatus.INITIATED.getCode(), dashboardReportDto -> dashboardReportDto.setInitiatedQuantity(1));
        incrementQuantityToProcessStatus.put(ProcessStatus.PROCESSING.getCode(), dashboardReportDto -> dashboardReportDto.setProcessingQuantity(1));
        incrementQuantityToProcessStatus.put(ProcessStatus.SUCCESS.getCode(), dashboardReportDto -> dashboardReportDto.setSuccessQuantity(1));
        incrementQuantityToProcessStatus.put(ProcessStatus.OVERDUE.getCode(), dashboardReportDto -> dashboardReportDto.setOverdueQuantity(1));
        incrementQuantityToProcessStatus.put(ProcessStatus.FAILED.getCode(), dashboardReportDto -> dashboardReportDto.setFailedQuantity(1));
    }

    public void add(ProcessType processType, DashboardReportDto newReport) {
        DashboardReportDto dashboardReportFound = this.getByProcessType(processType);

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus
                    .forEach((integer, dashboardReportDtoConsumer) -> dashboardReportDtoConsumer.accept(dashboardReportFound));
        } else {
            dashboardCache.put(processType.getCode(), newReport);
        }
    }

    public ConcurrentMap<Integer, DashboardReportDto> getAllDashboardReports() {
        return dashboardCache;
    }

    public DashboardReportDto getByProcessType(ProcessType processType) {
        return dashboardCache.get(processType.getCode());
    }

    public void update(IndustrialProcessDto process) {
        DashboardReportDto dashboardReportFound = this.getByProcessType(ProcessType.valueOfCode(process.getProcessType()));

        Integer processStatus = Integer.valueOf(process.getProcessStatus());

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus.get(processStatus).accept(dashboardReportFound);
        } else {
            DashboardReportDto dashboardReportDto = new DashboardReportDto();
            dashboardCache.put(ProcessType.valueOfCode(process.getProcessType()).getCode(), dashboardReportDto);
        }
    }

    public int size() {
        return dashboardCache.size();
    }

    public void clearCache() {
        this.dashboardCache.clear();
    }

}
