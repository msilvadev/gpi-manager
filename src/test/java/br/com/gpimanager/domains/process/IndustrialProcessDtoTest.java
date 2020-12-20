package br.com.gpimanager.domains.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class IndustrialProcessDtoTest {

    private IndustrialProcessDto dto;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        dto = new IndustrialProcessDto(1, 1, 0, "Test",
                localDateTime, localDateTime);
    }

    @Test
    void getNumber() {
        assertThat(dto.getNumber()).isEqualTo(1);
    }

    @Test
    void setNumber() {
        dto.setNumber(12);
        assertThat(dto.getNumber()).isEqualTo(12);
    }

    @Test
    void getProcessType() {
        assertThat(dto.getProcessType()).isEqualTo(ProcessType.NEW_ORDER.getCode());
    }

    @Test
    void setProcessType() {
        dto.setProcessType(ProcessType.NEW_ORDER.getCode());
        assertThat(dto.getProcessType()).isEqualTo(ProcessType.NEW_ORDER.getCode());
    }

    @Test
    void getProcessStatus() {
        assertThat(dto.getProcessStatus()).isEqualTo(ProcessStatus.INITIATED.getCode());
    }

    @Test
    void setProcessStatus() {
        dto.setProcessStatus(ProcessStatus.PROCESSING.getCode());
        assertThat(dto.getProcessStatus()).isEqualTo(ProcessStatus.PROCESSING.getCode());
    }

    @Test
    void getDescription() {
        assertThat(dto.getDescription()).isEqualTo("Test");
    }

    @Test
    void setDescription() {
        dto.setDescription("Mock");
        assertThat(dto.getDescription()).isEqualTo("Mock");
    }

    @Test
    void getStart() {
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void setStart() {
        dto.setStart(this.localDateTime);
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void getEnd() {
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void setEnd() {
        dto.setEnd(this.localDateTime);
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void testEquals() {
        IndustrialProcessDto dtoToEqual = new IndustrialProcessDto(1, 1, 0, "Test",
                localDateTime, localDateTime);

        assertThat(dto.equals(dtoToEqual)).isTrue();
    }

    @Test
    void testHashCode() {
        IndustrialProcessDto dtoToHash = new IndustrialProcessDto(1, 1, 0, "Test",
                localDateTime, localDateTime);

        assertThat(dto.hashCode() == dtoToHash.hashCode()).isTrue();
    }
}