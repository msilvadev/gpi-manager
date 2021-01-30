package br.com.gpimanager.services;

import br.com.gpimanager.domains.process.*;
import br.com.gpimanager.services.dashboard.DashboardReportService;
import br.com.gpimanager.services.process.IndustrialProcessManagerService;
import br.com.gpimanager.services.process.IndustrialProcessManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class IndustrialProcessManagerServiceImplTest {

    @InjectMocks
    private IndustrialProcessManagerService service;

    private IndustrialProcessDto dto;
    private IndustrialProcess process;
    private final LocalDateTime start = LocalDateTime.now();
    private final List<IndustrialProcessDto> listToSave = new ArrayList<>();
    private final List<IndustrialProcess> listSaved = new ArrayList<>();

    @BeforeEach
    void setUp() {
        IndustrialProcessBuilder builder = new IndustrialProcessBuilder();
        process = builder.withProcessType(ProcessType.NEW_ORDER).withDescription("Test").build();
        listSaved.add(process);

        dto = new IndustrialProcessDto(0, 1, 0, "Test", start, null);
        listToSave.add(dto);

        IndustrialProcessRepository repository = Mockito.mock(IndustrialProcessRepository.class);
        DashboardReportService dashboardReportService = Mockito.mock(DashboardReportService.class);

        service = new IndustrialProcessManagerServiceImpl(repository, dashboardReportService);

        when(repository.save(Mockito.any(IndustrialProcess.class))).thenReturn(process);
        when(repository.saveAll(Mockito.any(Iterable.class))).thenReturn(listSaved);
        when(repository.findAll()).thenReturn(listSaved);
    }

    @Test
    void listAllProcesses() {
        List<IndustrialProcessDto> result = service.listAllProcesses();

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }

    @Test
    void saveIndustrialProcess() {
        IndustrialProcessDto result = service.saveIndustrialProcess(dto);

        assertThat(result.getNumber()).isEqualTo(process.getNumber());
        assertThat(result.getProcessStatus()).isEqualTo(process.getProcessStatus().getCode());
        assertThat(result.getProcessType()).isEqualTo(process.getProcessType().getCode());
        assertThat(result.getDescription()).isEqualTo(process.getDescription());
        assertThat(result.getStart()).isEqualTo(process.getStart());
    }

    @Test
    void testSaveIndustrialProcess() {
        List<IndustrialProcessDto> result = service.saveIndustrialProcess(listToSave);

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }
}