package br.com.gpimanager.services.process;

import br.com.gpimanager.domains.process.IndustrialProcessDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import scala.util.parsing.json.JSONObject;

import java.util.List;

@Component
public class IndustrialProcessActiveMQService extends RouteBuilder {

    private final static Logger logger = LoggerFactory.getLogger(IndustrialProcessActiveMQService.class);

    @Value("${spring.activemq.queue.name}")
    private String queueName;

    private final IndustrialProcessManagerService service;

    public IndustrialProcessActiveMQService(IndustrialProcessManagerService service) {
        this.service = service;
    }

    @Override
    public void configure() throws Exception {
        from("activemq:" + queueName)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String bodyIn = (String) exchange.getIn().getBody();

                        ObjectMapper mapper = new ObjectMapper();
                        IndustrialProcessDto processDto = mapper.readValue(bodyIn, IndustrialProcessDto.class);

                        service.saveIndustrialProcess(processDto);
                    }
                })
                .log(LoggingLevel.INFO, logger, "New process received from activemq" + "${body}")
                .end();
    }

}