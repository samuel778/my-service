package com.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author saml
 */
@SpringBootApplication
@EnableTransactionManagement
public class BizApiServiceApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(BizApiServiceApplication.class, args);
    }

    /**
     * 注入线程池
     *
     * @return TaskExecutor
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int process = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(process);
        executor.setMaxPoolSize(process * 2);
        return executor;
    }

}

