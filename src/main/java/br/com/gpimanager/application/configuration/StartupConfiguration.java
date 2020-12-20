package br.com.gpimanager.application.configuration;

import br.com.gpimanager.domains.process.IndustrialProcessDto;
import br.com.gpimanager.services.DashboardReportService;
import br.com.gpimanager.services.IndustrialProcessManagerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.gpimanager.domains")
public class StartupConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfiguration.class);

    private final DashboardReportService dashboardReportService;

    public StartupConfiguration(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @Bean("initialLoad")
    CommandLineRunner initialLoad(IndustrialProcessManagerService service) {
        return args -> {
            // read json file and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<IndustrialProcessDto>> typeReference = new TypeReference<List<IndustrialProcessDto>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/process.json");
            try {
                List<IndustrialProcessDto> processDtos = mapper.readValue(inputStream,typeReference);
                service.saveIndustrialProcess(processDtos);

                dashboardReportService.initializeCache();

                LOGGER.info("Load json with mock data to test!");
            } catch (IOException e){
                LOGGER.info("Failed to load json data to test from /json/process.json, {}", e.getMessage());
            }
        };
    }
}
