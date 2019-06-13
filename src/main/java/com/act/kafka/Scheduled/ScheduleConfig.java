//package com.act.kafka.Scheduled;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
//import java.util.concurrent.Executor;
//
///**
// * @ClassName ScheduleConfig
// * @Description 异步定时任务配置
// * @Autor Administrator
// * @Date 2019/4/18 11:20
// * @Version 1.0
// **/
//
//@Configuration
//@EnableScheduling
//public class ScheduleConfig implements SchedulingConfigurer,AsyncConfigurer {
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        TaskScheduler taskScheduler = taskScheduler();
//        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
//    }
//
//    /** 定时任务多线程处理 */
//    @Bean(destroyMethod = "shutdown")
//    public ThreadPoolTaskScheduler taskScheduler(){
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(20);
//        scheduler.setThreadNamePrefix("task-");
//        scheduler.setAwaitTerminationSeconds(60);
//        scheduler.setWaitForTasksToCompleteOnShutdown(true);
//        return scheduler;
//    }
//
//    @Override
//    public Executor getAsyncExecutor() {
//        Executor executor = taskScheduler();
//        return executor;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return new SimpleAsyncUncaughtExceptionHandler();
//    }
//}
