package bungae.thunder.cakey;

import bungae.thunder.cakey.repository.CakeRepository;
import bungae.thunder.cakey.repository.MemoryCakeRepository;
import bungae.thunder.cakey.repository.MemoryMessageRepository;
import bungae.thunder.cakey.repository.MemoryReportRepository;
import bungae.thunder.cakey.repository.MemoryUserRepository;
import bungae.thunder.cakey.repository.MessageRepository;
import bungae.thunder.cakey.repository.ReportRepository;
import bungae.thunder.cakey.repository.UserRepository;
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

