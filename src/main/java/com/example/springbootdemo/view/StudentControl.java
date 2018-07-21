package com.example.springbootdemo.view;

import com.example.springbootdemo.pojo.mysql.Student;
import com.example.springbootdemo.pojo.oracle.Emp;
import com.example.springbootdemo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/student",produces="text/plain;charset=UTF-8")
@Api(value = "DemoController",tags = {"功能演示控制器123"})
public class StudentControl {
    @Autowired
    StudentService studentService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "访问项目首页",notes = "springboot不支持直接访问静态页面,所有需要通过控制器跳转")
    @GetMapping("/stu")
    public String select(){
        List<Student> s = studentService.selectAll();
//        List<Emp> e = studentService.selectEmpAll();
        System.out.println(s);
//        System.out.println(e);
        return "哈哈哈";
    }
    @GetMapping("/student")
    public  String add(){
        rabbitTemplate.convertAndSend("spring.rabbitmq.exchange","spring.rabbitmq.queue","hello");
        return "";
    }
}
