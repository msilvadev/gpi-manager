package br.com.gpimanager.domains.process;

public class IndustrialProcessDtoBuilder {

    private IndustrialProcessDtoBuilder(){}

    public static IndustrialProcessDto build(IndustrialProcess industrialProcess) {
        return new IndustrialProcessDto(
                industrialProcess.getNumber(),
                industrialProcess.getProcessType().getCode(),
                industrialProcess.getProcessStatus().getCode(),
                industrialProcess.getDescription(),
                industrialProcess.getStart(),
                industrialProcess.getEnd() != null ? industrialProcess.getEnd() : null
        );
    }

    public static IndustrialProcess buildDtoToIndustrialProcess(IndustrialProcessDto dto) {
        IndustrialProcessBuilder builder = new IndustrialProcessBuilder();

        return builder
                .withNumber(dto.getNumber())
                .withDescription(dto.getDescription())
                .withProcessType(ProcessType.valueOfCode(dto.getProcessType()))
                .withProcessStatus(ProcessStatus.valueOfCode(dto.getProcessStatus()))
                .build();
    }

}
