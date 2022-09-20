package bungae.thunder.cakey;

import bungae.thunder.cakey.repository.MemoryUserRepository;
import bungae.thunder.cakey.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public UserRepository userRepository() {
        // 추후에 해당 부분은 PostgresqlUserRepository로 교체
        return new MemoryUserRepository();
    }
}
