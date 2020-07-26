package com.supriyanta.interviewprep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@Configuration
@EnableJpaAuditing
public class AuditModelConfig {
}
