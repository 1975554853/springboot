package com.example.springbootdemo.service.impl;


import com.example.springbootdemo.mapper.mysql.StudentInfoMapper;
import com.example.springbootdemo.mapper.oracle.EmpMapper;
import com.example.springbootdemo.pojo.mysql.Student;
import com.example.springbootdemo.pojo.oracle.Emp;
import com.example.springbootdemo.service.StudentService;
import com.example.springbootdemo.system.listener.StudentLoginEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentInfoMapper studentInfoMapper;
    @Autowired
    EmpMapper empMapper;
    @Autowired
    ApplicationContext applicationContext;


    @Override
    public List<Student> selectAll() {
       List<Student> students = studentInfoMapper.SelectAll();
       for (Student student :students) {
           if (student != null) {
               applicationContext.publishEvent(new StudentLoginEvent(this, student));
           }

       }
        return students;
    }

    @Override
    public List<Emp> selectEmpAll() {
        return empMapper.selectEmpAll();
    }

    @RabbitListener(queues = "spring.rabbitmq.queue")
    public void customer(String  message){
        System.out.println(message);
    }
}