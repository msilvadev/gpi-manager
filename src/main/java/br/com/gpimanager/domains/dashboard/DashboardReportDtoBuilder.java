package br.com.gpimanager.domains.dashboard;

public class DashboardReportDtoBuilder {

    private int initiatedQuantity;
    private int processingQuantity;
    private int successQuantity;
    private int overdueQuantity;
    private int failedQuantity;

    public DashboardReportDtoBuilder setInitiatedQuantity(int initiatedQuantity) {
        this.initiatedQuantity = initiatedQuantity;
        return this;
    }

    public DashboardReportDtoBuilder setProcessingQuantity(int processingQuantity) {
        this.processingQuantity = processingQuantity;
        return this;
    }

    public DashboardReportDtoBuilder setSuccessQuantity(int successQuantity) {
        this.successQuantity = successQuantity;
        return this;
    }

    public DashboardReportDtoBuilder setOverdueQuantity(int overdueQuantity) {
        this.overdueQuantity = overdueQuantity;
        return this;
    }

    public DashboardReportDtoBuilder setFailedQuantity(int failedQuantity) {
        this.failedQuantity = failedQuantity;
        return this;
    }

    public DashboardReportDto build() {
        return new DashboardReportDto(this.initiatedQuantity,
                this.processingQuantity,
                this.successQuantity,
                this.overdueQuantity,
                this.failedQuantity);
    }
}
