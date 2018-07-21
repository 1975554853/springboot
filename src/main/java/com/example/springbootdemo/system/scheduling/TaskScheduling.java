package com.example.springbootdemo.system.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * 任务调度类
 */
@Component
public class TaskScheduling {

    /**
     * 定时任务调度,需要在你定时执行的方法上加上 @Scheduled,
     * 值得注意的是,@Scheduled()注解内部参数,
     * Schedule() 的参数可以分为3类:
     *      1. fixedDelayString / fixedDelay
     *      2. fixedRateString /  fixedRate
     *
     *         Scheduled(fixedRateString = "${task.scheduling.eat}")
     *         Scheduled(fixedDelay = 5)
     *
     *         带有string的参数可以通过el表达式读取配置文件,后者只能手动写时间(单位为秒)
     *         1和2的区别在于 : 前者会在当前任务结束后执行,比如定时任务设置为1个小时执行一次,
     *                       那么 fixedDelayString / fixedDelay 会在任务结束后一个小时执行,
     *                       而后者 fixedRateString /  fixedRate 则是会在任务开始后一个小时执行
     *      3. cron 是一个比较特殊的参数,他同样是用来描述任务执行时间的,只不过,
     *          它的参数使用 cron 表达式 , cron 表达式 是专门为任务调度框架设计的一种时间表达式,
     *          它可以表达的更精确,最早他是为 Quartz 任务调度框架设计的,最后推广开来,
     *          成为了任务调度方向上的重要工具;关于 cron 表达式,我们可以直接去访问
     *          一些工具网站,在线生成 cron 表达式 ,
     *          比如 : http://cron.qqe2.com
     *
     */

//    @Scheduled(fixedDelayString = "${task.scheduling.sleep}")
//    public void sleep(){
//        System.out.println("每天睡觉");
//    }

//
//    @Scheduled(fixedRate = 10000)
//    public void eat(){
//        System.out.println("每天吃饭");
//    }
//
//    @Scheduled(cron = "0/10 * * * * ? ")
//    public void run(){
//        System.out.println("每天跑步");
//    }

}
