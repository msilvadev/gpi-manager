package br.com.gpimanager.domains.dashboard;

import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.domains.process.ProcessType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DashboardReportRepositoryTest {

    private DashboardReportRepository repository;
    private final ProcessType processType = ProcessType.NEW_ORDER;
    private DashboardReportDto dto;
    private IndustrialProcessDto industrialProcessDto;

    @BeforeEach
    void setUp() {
        repository = new DashboardReportRepository();
        repository.initialize();

        DashboardReportDtoBuilder builder = new DashboardReportDtoBuilder();
        this.dto = builder
                .setInitiatedQuantity(1)
                .setFailedQuantity(1)
                .setOverdueQuantity(1)
                .setProcessingQuantity(1)
                .setSuccessQuantity(1)
                .build();

        repository.add(processType, this.dto);

        industrialProcessDto = new IndustrialProcessDto(1, 1, 0, "Test",
                LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void add() {
        repository.add(ProcessType.NEW_ORDER, this.dto);

        assertThat(repository.getAllDashboardReports()).hasSize(1);
    }

    @Test
    void getAllDashboardReports() {
        assertThat(repository.getAllDashboardReports()).isNotEmpty();
    }

    @Test
    void size() {
        assertThat(repository.getAllDashboardReports()).hasSize(1);
    }

    @Test
    void update() {
        repository.update(industrialProcessDto);

        DashboardReportDto result = repository.getByProcessType(this.processType);

        assertThat(result.getInitiatedQuantity()).isEqualTo(2);
    }

    @Test
    void tryUpdateWhenNotExistYet() {
        repository.update(industrialProcessDto);

        DashboardReportDto result = repository.getByProcessType(this.processType);

        assertThat(result.getFailedQuantity()).isEqualTo(1);
    }
}