package br.com.gpimanager.domains.process;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProcessTypeTest {

    @Test
    void valueOfCode() {
        assertThat(ProcessType.valueOfCode(1).getDescription()).isEqualTo(ProcessType.NEW_ORDER.getDescription());
        assertThat(ProcessType.valueOfCode(2).getDescription()).isEqualTo(ProcessType.DELIVERY_TISSUE.getDescription());
    }

    @Test
    void values() {
        assertThat(ProcessType.values()).isNotNull();
    }

    @Test
    void valueOf() {
        assertThat(ProcessType.valueOf("NEW_ORDER")).isEqualTo(ProcessType.NEW_ORDER);
        assertThat(ProcessType.valueOf("DELIVERY_TISSUE")).isEqualTo(ProcessType.DELIVERY_TISSUE);
    }
}