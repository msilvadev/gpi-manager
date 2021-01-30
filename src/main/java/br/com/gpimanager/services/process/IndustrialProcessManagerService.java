package br.com.gpimanager.services.process;

import br.com.gpimanager.domains.process.IndustrialProcessDto;

import java.util.List;

public interface IndustrialProcessManagerService {

    List<IndustrialProcessDto> listAllProcesses();

    IndustrialProcessDto saveIndustrialProcess(IndustrialProcessDto industrialProcessDto);

    List<IndustrialProcessDto> saveIndustrialProcess(List<IndustrialProcessDto> industrialProcessDtoList);

}
