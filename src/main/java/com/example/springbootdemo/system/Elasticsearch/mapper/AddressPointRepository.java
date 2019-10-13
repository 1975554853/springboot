package com.example.springbootdemo.system.Elasticsearch.mapper;

import com.example.springbootdemo.system.Elasticsearch.bean.AddressPointDto;
import com.example.springbootdemo.system.Elasticsearch.bean.VehicleDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: springbootdemo
 * @description:
 * @author: andy
 * @create: 2019-10-12 15:11
 */
@Repository
public interface AddressPointRepository extends ElasticsearchRepository<AddressPointDto, Long> {
}
