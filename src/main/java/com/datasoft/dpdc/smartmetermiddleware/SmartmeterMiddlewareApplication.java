package com.datasoft.dpdc.smartmetermiddleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SmartmeterMiddlewareApplication {

    @Bean
    public TaskExecutor taskExecutor() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setKeepAliveSeconds(360);
        taskExecutor.setThreadNamePrefix("DPDC-Middleware");
        taskExecutor.setMaxPoolSize(100000);
        taskExecutor.setDaemon(true);
        return taskExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartmeterMiddlewareApplication.class, args);
    }
}
