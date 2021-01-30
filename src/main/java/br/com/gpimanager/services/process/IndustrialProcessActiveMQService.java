package br.com.gpimanager.services.process;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IndustrialProcessActiveMQService extends RouteBuilder {

    private final static Logger logger = LoggerFactory.getLogger(IndustrialProcessActiveMQService.class);

    @Value("${spring.activemq.queue.name}")
    private String queueName;

    @Override
    public void configure() throws Exception {
        from("activemq:" + queueName)
                .log(LoggingLevel.INFO, logger, "${body}")
                .end();
    }

}