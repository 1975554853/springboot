package com.example.springbootdemo.system.Elasticsearch.mapper;

import com.example.springbootdemo.system.Elasticsearch.bean.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeDao extends ElasticsearchRepository<Employee,String> {
}
