package br.com.gpimanager.services.process;

import br.com.gpimanager.domains.process.IndustrialProcess;
import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.domains.process.IndustrialProcessDtoBuilder;
import br.com.gpimanager.domains.process.IndustrialProcessRepository;
import br.com.gpimanager.services.dashboard.DashboardReportService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustrialProcessManagerServiceImpl implements IndustrialProcessManagerService {

    private final IndustrialProcessRepository repository;
    private final DashboardReportService dashboardReportService;

    public IndustrialProcessManagerServiceImpl(IndustrialProcessRepository repository,
                                               DashboardReportService dashboardReportService) {
        this.repository = repository;
        this.dashboardReportService = dashboardReportService;
    }

    @Override
    public List<IndustrialProcessDto> listAllProcesses() {
        List<IndustrialProcess> all = repository.findAll();

        return all.stream()
                .map(IndustrialProcessDtoBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized IndustrialProcessDto saveIndustrialProcess(IndustrialProcessDto industrialProcessDto) {
        IndustrialProcessDto saved = IndustrialProcessDtoBuilder.build(repository.save(IndustrialProcessDtoBuilder
                .buildDtoToIndustrialProcess(industrialProcessDto)));

        dashboardReportService.updateCacheDashboarReport(saved);

        return saved;
    }

    @Override
    @Transactional
    public synchronized List<IndustrialProcessDto> saveIndustrialProcess(List<IndustrialProcessDto> industrialProcessDtoList) {
        List<IndustrialProcess> industrialProcessesSaved = repository.saveAll(industrialProcessDtoList.stream()
                .map(IndustrialProcessDtoBuilder::buildDtoToIndustrialProcess)
                .collect(Collectors.toList()));

        return industrialProcessesSaved.stream()
                .map(IndustrialProcessDtoBuilder::build)
                .collect(Collectors.toList());
    }

}
