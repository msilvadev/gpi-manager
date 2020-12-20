package br.com.gpimanager.domains.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DashboardReportDtoTest {

    private DashboardReportDto dashboardReportDto;

    @BeforeEach
    void setUp() {
        dashboardReportDto = new DashboardReportDto(
                1, 1,
                1, 1 ,1);
    }

    @Test
    void getInitiatedQuantity() {
        assertThat(dashboardReportDto.getInitiatedQuantity()).isEqualTo(1);
    }

    @Test
    void getProcessingQuantity() {
        assertThat(dashboardReportDto.getProcessingQuantity()).isEqualTo(1);
    }

    @Test
    void getSuccessQuantity() {
        assertThat(dashboardReportDto.getSuccessQuantity()).isEqualTo(1);
    }

    @Test
    void getOverdueQuantity() {
        assertThat(dashboardReportDto.getOverdueQuantity()).isEqualTo(1);
    }

    @Test
    void getFailedQuantity() {
        assertThat(dashboardReportDto.getFailedQuantity()).isEqualTo(1);
    }
}