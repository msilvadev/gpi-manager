package br.com.gpimanager.domains.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class IndustrialProcessBuilderTest {

    private IndustrialProcessBuilder builder;
    private final LocalDateTime start = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        builder = new IndustrialProcessBuilder();
    }

    @Test
    void withNumber() {
        IndustrialProcess build = builder.withNumber(1).build();
        assertThat(build.getNumber()).isEqualTo(1);
    }

    @Test
    void withProcessType() {
        IndustrialProcess build = builder.withProcessType(ProcessType.NEW_ORDER).build();
        assertThat(build.getProcessType()).isEqualTo(ProcessType.NEW_ORDER);
    }

    @Test
    void withProcessStatus() {
        IndustrialProcess build = builder.withProcessStatus(ProcessStatus.SUCCESS).build();
        assertThat(build.getProcessStatus()).isEqualTo(ProcessStatus.SUCCESS);
    }

    @Test
    void withDescription() {
        IndustrialProcess build = builder.withDescription("Test").build();
        assertThat(build.getDescription()).isEqualTo("Test");
    }

    @Test
    void withStart() {
        IndustrialProcess build = builder.withStart(this.start).build();
        assertThat(build.getStart()).isEqualTo(this.start);
    }

    @Test
    void build() {
        IndustrialProcess build = builder.withNumber(1)
                .withProcessType(ProcessType.NEW_ORDER)
                .withProcessStatus(ProcessStatus.INITIATED)
                .withDescription("Test")
                .withStart(this.start)
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isEqualTo(1);
        assertThat(build.getProcessType()).isEqualTo(ProcessType.NEW_ORDER);
        assertThat(build.getProcessStatus()).isEqualTo(ProcessStatus.INITIATED);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isEqualTo(this.start);
        assertThat(build.getEnd()).isNull();
    }

    @Test
    void shouldBeBuildWithDefaultValuesNumberProcessTypeProcessStatusAndStart() {
        IndustrialProcess build = builder
                .withProcessType(ProcessType.NEW_ORDER)
                .withDescription("Test")
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isZero();
        assertThat(build.getProcessType()).isEqualTo(ProcessType.NEW_ORDER);
        assertThat(build.getProcessStatus()).isEqualTo(ProcessStatus.INITIATED);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isNotNull();
    }
}