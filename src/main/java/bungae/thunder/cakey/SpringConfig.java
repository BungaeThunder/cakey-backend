package bungae.thunder.cakey;

import bungae.thunder.cakey.cake.repository.CakeRepository;
import bungae.thunder.cakey.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.message.repository.MemoryMessageRepository;
import bungae.thunder.cakey.message.repository.MessageRepository;
import bungae.thunder.cakey.report.repository.MemoryReportRepository;
import bungae.thunder.cakey.report.repository.ReportRepository;
import bungae.thunder.cakey.user.repository.MemoryUserRepository;
import bungae.thunder.cakey.user.repository.UserRepository;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
