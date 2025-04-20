package nevek.grupovendas.gerenciador_vendas.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("nevek.grupovendas.gerenciador_vendas.domain")
@EnableJpaRepositories("nevek.grupovendas.gerenciador_vendas.repos")
@EnableTransactionManagement
public class DomainConfig {
}
