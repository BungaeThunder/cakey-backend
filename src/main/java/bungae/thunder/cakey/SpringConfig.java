package bungae.thunder.cakey;

import bungae.thunder.cakey.cake.repository.CakeRepository;
import bungae.thunder.cakey.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.user.repository.MemoryUserRepository;
import bungae.thunder.cakey.user.repository.UserRepository;
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
    public UserRepository userRepository() {
        // 추후에 해당 부분은 PostgresqlUserRepository로 교체
        return new MemoryUserRepository();
    }
}
