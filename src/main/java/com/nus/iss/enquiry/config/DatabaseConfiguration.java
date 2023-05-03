package com.nus.iss.enquiry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.nus.iss.enquiry.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class DatabaseConfiguration {
}