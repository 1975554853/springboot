package com.example.springbootdemo.system.listener;


import com.example.springbootdemo.pojo.mysql.Student;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * 监听器,这个类的作用就是不断的监听系统中是否新的事件发布,
 * 以及事件是否被触发
 */
@Component
public class SystemListener {

    /**
     * 监听用户登录事件,如果用户登录,
     * 处理对应的业务
     * @param studentLoginEvent
     */
    @EventListener
    public void studentLoginListener(StudentLoginEvent studentLoginEvent){
        Student student = studentLoginEvent.getStudent();
        System.out.println(student.toString());
    }

}