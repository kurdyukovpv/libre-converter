package ru.libre.converter;

import lombok.extern.slf4j.Slf4j;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class LibreOfficeManager {

    private static final int PORT = 2002;

    @Value("${libre.office.home}")
    private String libreHome;

    @Value("${libre.office.process-count:1}")
    private Integer processCount;

    private OfficeManager officeManager;

    @Bean
    public Converter converter() {
        log.info("Start Libre Office...");
        officeManager = new DefaultOfficeManagerConfiguration()
                .setOfficeHome(libreHome)
                .setPortNumbers(IntStream.range(PORT, PORT + processCount).toArray())
                .buildOfficeManager();
        officeManager.start();
        log.info("Libre Office started");
        return new Converter(officeManager);
    }

    @PreDestroy
    private void destroy() {
        officeManager.stop();
    }
}
