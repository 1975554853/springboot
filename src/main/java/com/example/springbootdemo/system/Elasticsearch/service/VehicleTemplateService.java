package com.example.springbootdemo.system.Elasticsearch.service;

import com.example.springbootdemo.system.Elasticsearch.bean.VehicleDto;

import java.util.List;

/**
 * @program: springbootdemo
 * @description:
 * @author: andy
 * @create: 2019-10-12 11:03
 */
public interface VehicleTemplateService {

    public void bulkIndex(List<VehicleDto> personList);

    public List<VehicleDto> queryForList(double lat, double lon);

    public List<VehicleDto> queryDto();

}
