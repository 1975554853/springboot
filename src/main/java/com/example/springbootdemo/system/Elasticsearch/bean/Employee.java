package com.example.springbootdemo.system.Elasticsearch.bean;

import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.io.Serializable;

/**
 * @program: springbootdemo
 * @description:
 * @author: andy
 * @create: 2019-10-12 15:22
 */
@Data
@Document(indexName = "employee",type = "employee",shards = 1,replicas = 0)
public class Employee implements Serializable {

    private static final long serialVersionUID = -5483287283894740770L;
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String firstName;


    @GeoPointField
    private GeoPoint geo;

}
