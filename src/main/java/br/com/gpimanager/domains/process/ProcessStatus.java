package br.com.gpimanager.domains.process;

import java.util.HashMap;
import java.util.Map;

public enum ProcessStatus {
    INITIATED(0,"Iniciado"),
    PROCESSING(1,"Em processamento"),
    SUCCESS(2,"Finalizado com sucesso"),
    OVERDUE(3,"Processo em atraso"),
    FAILED(4,"Falha no processo");

    private static final Map<Integer, ProcessStatus> map = new HashMap<>();

    static {
        for(ProcessStatus status : values()){
            map.put(status.code, status);
        }
    }

    private final int code;
    private final String description;

    ProcessStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProcessStatus valueOfCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
