package br.com.gpimanager.domains.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndustrialProcessDto implements Serializable {

    private static final long serialVersionUID = 3263614731673211493L;

    private long number;
    private int processType;
    private int processStatus;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime end;

    public IndustrialProcessDto(long number, int processType,
                                int processStatus, String description,
                                LocalDateTime start, LocalDateTime end) {
        this.number = number;
        this.processType = processType;
        this.processStatus = processStatus;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public IndustrialProcessDto() { }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndustrialProcessDto that = (IndustrialProcessDto) o;
        return number == that.number && processType == that.processType && processStatus == that.processStatus && Objects.equals(description, that.description) && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, processType, processStatus, description, start, end);
    }
}
