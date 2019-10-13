package com.example.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;
    protected  Logger LOGGER=Logger.getLogger(SpringbootdemoApplicationTests.class.getName());

    @Test
    public void contextLoads() {
        rabbitMessagingTemplate.convertAndSend("spring.rabbitmq.exchange","spring.rabbitmq.queue","sdfasfsdfafsf");
    }
    @Value("${redis.host}")
    String host;
    @Test
    public void testDistributedLock(){
        System.out.println(host);
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                .setAddress(host);
        RedissonClient redisson = Redisson.create(config);
        IntStream.rangeClosed(1,5)
                .parallel()
                .forEach(i -> {
                    executeLock(redisson);
                });
        executeLock(redisson);
    }

    public void executeLock(RedissonClient redisson){
        RLock lock = redisson.getLock("key");
        boolean locked = false;
        try{
            LOGGER.info("try lock");
            locked = lock.tryLock(5, 20, TimeUnit.SECONDS);
//            locked = lock.tryLock(1,2,TimeUnit.MINUTES);
            LOGGER.info("get lock result:{}" + locked);
            if(locked){
                System.out.println(locked);
                Thread.sleep(2000);//等待6秒
                LOGGER.info("get lock and finish");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            LOGGER.info("enter unlock");
            if(locked){
                lock.unlock();
            }
        }
    }
    @Test
    public void test() {
        Config config = new Config();
        SingleServerConfig singleSerververConfig = config.useSingleServer();
        singleSerververConfig.setAddress(host);
        //  singleSerververConfig.setPassword("redis");
        //redisson客户端
        RedissonClient redisson = Redisson.create(config);
        RBucket<String> bucket = redisson.getBucket("myLockkey");
        System.out.println(bucket.get());
        boolean locked=false;
        RLock lock = redisson.getLock("new");
        try {
            // while (true) {
            LOGGER.info("try lock");
            locked = lock.tryLock();
//            locked = lock.tryLock(1,2,TimeUnit.MINUTES);
            LOGGER.info("get lock result:{}" + locked);
            if (locked) {
                //这里是要执行的功能，能能是多线程
            }
            // }
        } catch (Exception e) {
            LOGGER.info("当前线程去解锁其他线程，报错");
        } finally {
            LOGGER.info("enter unlock");
            if (locked) {
                lock.unlock();
            }
        }
    }

}
