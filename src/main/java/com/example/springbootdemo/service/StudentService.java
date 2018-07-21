package com.example.springbootdemo.service;


import com.example.springbootdemo.pojo.mysql.Student;
import com.example.springbootdemo.pojo.oracle.Emp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fly
 * @since 2018-06-29
 */
public interface StudentService {

    public List<Student> selectAll();
    public List<Emp> selectEmpAll();

}