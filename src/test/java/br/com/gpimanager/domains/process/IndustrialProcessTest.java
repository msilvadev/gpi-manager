package br.com.gpimanager.domains.process;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IndustrialProcessTest {

    @Test
    void constructor() {
        IndustrialProcess industrialProcess = new IndustrialProcess();
        assertThat(industrialProcess instanceof IndustrialProcess).isTrue();
    }

    @Test
    void getEnd() {
        IndustrialProcessBuilder builder = new IndustrialProcessBuilder();
        IndustrialProcess build = builder
                .withDescription("Test")
                .build();

        LocalDateTime end = LocalDateTime.now();
        build.setEnd(end);

        assertThat(build.getEnd()).isEqualTo(end);
    }

    @Test
    void industrialProcessToString() {
        IndustrialProcessBuilder builder = new IndustrialProcessBuilder();
        IndustrialProcess build = builder
                .withDescription("Test")
                .build();

        assertThat(build.toString()).isNotBlank();
    }
}