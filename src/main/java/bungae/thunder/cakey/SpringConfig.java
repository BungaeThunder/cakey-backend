package bungae.thunder.cakey;

import bungae.thunder.cakey.domain.cake.repository.CakeRepository;
import bungae.thunder.cakey.domain.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.domain.message.repository.MemoryMessageRepository;
import bungae.thunder.cakey.domain.message.repository.MessageRepository;
import bungae.thunder.cakey.domain.report.repository.MemoryReportRepository;
import bungae.thunder.cakey.domain.report.repository.ReportRepository;
import bungae.thunder.cakey.domain.user.repository.MemoryUserRepository;
import bungae.thunder.cakey.domain.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class SpringConfig {
    @Bean
    public CakeRepository cakeRepository() {
        return new MemoryCakeRepository();
    }

    @Bean
    public UserRepository userRepository() {
        // 추후에 해당 부분은 PostgresqlUserRepository로 교체
        return new MemoryUserRepository();
    }
    
    @Bean
    public MessageRepository messageRepository() {
        // TODO: DB Repository
        return new MemoryMessageRepository(new HashMap<>());
    }
    
    @Bean
    public ReportRepository reportRepository() {
        return new MemoryReportRepository();
    }
}

