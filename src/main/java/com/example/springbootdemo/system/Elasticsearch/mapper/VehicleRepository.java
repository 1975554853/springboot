package com.example.springbootdemo.system.Elasticsearch.mapper;

import com.example.springbootdemo.system.Elasticsearch.bean.VehicleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springbootdemo
 * @description:
 * @author: andy
 * @create: 2019-10-12 10:58
 */
@Repository
public interface VehicleRepository extends ElasticsearchRepository<VehicleDto, Long> {

    /**
     * @return
     */
    List<VehicleDto> findByCarDriver(String carDriver, Pageable pageable);

    /**
     * @param name
     * @return
     */
    List<VehicleDto> findByAddressPointDtoName(String name,Pageable pageable);

    /**
     * @param id1
     * @param id2
     * @return
     */
    List<VehicleDto> findByCarDriverAndPrice(String carDriver,int price,Pageable pageable);

    /**
     * @return
     */
    List<VehicleDto> findByCarDriverOrCarType(String carDriver,String carType,Pageable pageable);

    /**
     * @param id1
     * @param id2
     * @return
     */
    List<VehicleDto> findByPriceBetween(int price1, int price2);

}
