package bungae.thunder.cakey;

import bungae.thunder.cakey.cake.repository.CakeRepository;
import bungae.thunder.cakey.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.report.repository.MemoryReportRepository;
import bungae.thunder.cakey.report.repository.ReportRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class SpringConfig {
    @Bean
    public CakeRepository cakeRepository() {
        return new MemoryCakeRepository();
    }

    @Bean
    public ReportRepository reportRepository() {
        return new MemoryReportRepository();
    }
}
