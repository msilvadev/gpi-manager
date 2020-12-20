package br.com.gpimanager.domains.dashboard;

import java.io.Serializable;

public class DashboardReportDto implements Serializable {

    private static final long serialVersionUID = 607321184173085405L;

    private int initiatedQuantity;
    private int processingQuantity;
    private int successQuantity;
    private int overdueQuantity;
    private int failedQuantity;

    public DashboardReportDto(int initiatedQuantity, int processingQuantity, int successQuantity, int overdueQuantity, int failedQuantity) {
        this.initiatedQuantity = initiatedQuantity;
        this.processingQuantity = processingQuantity;
        this.successQuantity = successQuantity;
        this.overdueQuantity = overdueQuantity;
        this.failedQuantity = failedQuantity;
    }

    public DashboardReportDto() {}

    public void setInitiatedQuantity(int initiatedQuantity) {
        this.initiatedQuantity += initiatedQuantity;
    }

    public void setProcessingQuantity(int processingQuantity) {
        this.processingQuantity += processingQuantity;
    }

    public void setSuccessQuantity(int successQuantity) {
        this.successQuantity += successQuantity;
    }

    public void setOverdueQuantity(int overdueQuantity) {
        this.overdueQuantity += overdueQuantity;
    }

    public void setFailedQuantity(int failedQuantity) {
        this.failedQuantity += failedQuantity;
    }

    public int getInitiatedQuantity() {
        return initiatedQuantity;
    }

    public int getProcessingQuantity() {
        return processingQuantity;
    }

    public int getSuccessQuantity() {
        return successQuantity;
    }

    public int getOverdueQuantity() {
        return overdueQuantity;
    }

    public int getFailedQuantity() {
        return failedQuantity;
    }

    @Override
    public String toString() {
        return "DashboardReportDto{" +
                "initiatedQuantity=" + initiatedQuantity +
                ", processingQuantity=" + processingQuantity +
                ", successQuantity=" + successQuantity +
                ", overdueQuantity=" + overdueQuantity +
                ", failedQuantity=" + failedQuantity +
                '}';
    }
}
