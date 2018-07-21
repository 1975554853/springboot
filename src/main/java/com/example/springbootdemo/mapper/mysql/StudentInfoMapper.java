package com.example.springbootdemo.mapper.mysql;


import com.example.springbootdemo.pojo.mysql.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentInfoMapper {
    List<Student> SelectAll();
    Student SelectSingle(@Param("id") Integer id);
    Integer deleteById(Integer id);
    Integer addStudent(@Param("name") String name, @Param("sex") String sex, @Param("seat") Integer seat, @Param("desc") String desc);
    List<Student> selectlimt(@Param("page") Integer page, @Param("limit") Integer limit);
    Integer selectnum();
    int batchStudents(Student student);

}
