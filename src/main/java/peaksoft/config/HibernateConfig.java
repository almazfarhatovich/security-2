package peaksoft.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("peaksoft")
                .createEntityManager();
    }
}
