/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Configuration
@EnableR2dbcRepositories(repositoryBaseClass = ReactiveCrudRepository.class)
@EnableConfigurationProperties({ApiConfigProperties.class})
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
