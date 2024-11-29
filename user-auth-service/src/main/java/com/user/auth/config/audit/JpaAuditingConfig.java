/**
 * Author: Vinod Jagwani
 */
package com.user.auth.config.audit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;


@Slf4j
@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorProvider", modifyOnCreate = false)
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Principal::getName);
    }
}
