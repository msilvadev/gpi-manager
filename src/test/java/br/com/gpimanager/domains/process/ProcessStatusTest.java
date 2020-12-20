package br.com.gpimanager.domains.process;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProcessStatusTest {

    @Test
    void valueOfCode() {
        assertThat(ProcessStatus.valueOfCode(0).getDescription()).isEqualTo(ProcessStatus.INITIATED.getDescription());
        assertThat(ProcessStatus.valueOfCode(1).getDescription()).isEqualTo(ProcessStatus.PROCESSING.getDescription());
        assertThat(ProcessStatus.valueOfCode(2).getDescription()).isEqualTo(ProcessStatus.SUCCESS.getDescription());
        assertThat(ProcessStatus.valueOfCode(3).getDescription()).isEqualTo(ProcessStatus.OVERDUE.getDescription());
        assertThat(ProcessStatus.valueOfCode(4).getDescription()).isEqualTo(ProcessStatus.FAILED.getDescription());
    }

    @Test
    void values() {
        assertThat(ProcessStatus.values()).isNotNull();
    }

    @Test
    void valueOf() {
        assertThat(ProcessStatus.valueOf("INITIATED")).isEqualTo(ProcessStatus.INITIATED);
        assertThat(ProcessStatus.valueOf("PROCESSING")).isEqualTo(ProcessStatus.PROCESSING);
        assertThat(ProcessStatus.valueOf("SUCCESS")).isEqualTo(ProcessStatus.SUCCESS);
        assertThat(ProcessStatus.valueOf("OVERDUE")).isEqualTo(ProcessStatus.OVERDUE);
        assertThat(ProcessStatus.valueOf("FAILED")).isEqualTo(ProcessStatus.FAILED);
    }
}