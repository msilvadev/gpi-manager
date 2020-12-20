package br.com.gpimanager.domains.process;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROCESS")
public class IndustrialProcess implements Serializable {

    private static final long serialVersionUID = 7434897361855297228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_TYPE")
    private ProcessType processType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_STATUS")
    private ProcessStatus processStatus;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(name = "END_DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    public IndustrialProcess(long number, ProcessType processType,
                             ProcessStatus processStatus, String description, LocalDateTime start) {
        this.number = number;
        this.processType = processType;
        this.processStatus = processStatus;
        this.description = description;
        this.start = start;
    }

    public IndustrialProcess() {
    }

    public long getNumber() {
        return number;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "IndustrialProcess{" +
                "number=" + number +
                ", processType=" + processType +
                ", processStatus=" + processStatus +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
