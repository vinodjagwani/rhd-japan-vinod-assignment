/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

@Configuration
@EnableR2dbcRepositories(repositoryBaseClass = ReactiveSortingRepository.class)
@EnableConfigurationProperties({ApiConfigProperties.class})
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ReactiveGridFsTemplate reactiveGridFsTemplate(final ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory, final MappingMongoConverter mappingMongoConverter) {
        return new ReactiveGridFsTemplate(reactiveMongoDatabaseFactory, mappingMongoConverter);
    }

}
