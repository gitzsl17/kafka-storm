package com.act.kafka.Scheduled;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ScheduledTest
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/18 14:58
 * @Version 1.0
 **/
public class ScheduledTest {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Async
    @Scheduled(cron = "0 */1 * * * ?")
    public void executeRepaymentTask() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " <<< task two " + format.format(new Date()));
        Thread.sleep(3000);
    }

    @Async
    @Scheduled(cron = "*/2 * * * * ?")
    public void executeUpdateYqTask() {
        System.out.println(Thread.currentThread().getName() + " >>> task one " + format.format(new Date()));
    }
}
