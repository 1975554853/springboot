package com.example.springbootdemo.system.listener;


import com.example.springbootdemo.pojo.mysql.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author fly
 * 监听器: 监听器也是一个比较有用的功能,
 * 是观察者模式的典型实现,可以理解为当某个事件
 * 发生时去触发一些操作,监听器通常由3个部分组成,
 *  事件 -- 事件发布 -- 监听器,
 *  event 是我们的时间,意为当用户登录时,
 *  发布一个事件
 */
@Getter
public class StudentLoginEvent extends ApplicationEvent {


    private Student student;

    /**
     * 建立一个可以被监听的事件,必须实现ApplicationEvent,
     * 我们这里必须实现它的构造方法
     * @param source  必要的参数 , 它指的是发生事件的对象,所以在调用的时,可以用传入 this
     * @param student 值得注意,对于student这个参数是可选的,它要根据你具体的业务来选择传入多少参数
     */

    public StudentLoginEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }

}