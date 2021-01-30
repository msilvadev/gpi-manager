package br.com.gpimanager.controllers;

import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.services.IndustrialProcessManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industrial-process")
public class IndustrialProcessManagerController {

    private final IndustrialProcessManagerService service;

    public IndustrialProcessManagerController(IndustrialProcessManagerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IndustrialProcessDto>> listAllIndustrialProcess() {
        return ResponseEntity.ok(service.listAllProcesses());
    }

    @PostMapping
    public ResponseEntity<IndustrialProcessDto> saveIndustrialProcess(@RequestBody IndustrialProcessDto input) {
        return ResponseEntity.ok(service.saveIndustrialProcess(input));
    }
}
