package com.example.springbootdemo.mapper.oracle;

import com.example.springbootdemo.pojo.oracle.Emp;

import java.util.List;

public interface EmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    int deleteByPrimaryKey(Short empno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    int insert(EmpMapper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    int insertSelective(EmpMapper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    EmpMapper selectByPrimaryKey(Short empno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    int updateByPrimaryKeySelective(EmpMapper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.EMP
     *
     * @mbg.generated Sat Jun 23 16:46:55 CST 2018
     */
    int updateByPrimaryKey(EmpMapper record);

    List<Emp> selectEmpAll();
}