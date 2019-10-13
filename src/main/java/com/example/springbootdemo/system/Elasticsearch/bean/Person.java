package com.example.springbootdemo.system.Elasticsearch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @program: springbootdemo
 * @description:                   @Document 注解主要声明索引名、类型名、分片数量和备份数量
 *                                  @Field 注解主要声明字段对应ES的类型
 * @author: andy
 * @create: 2019-10-11 14:07
 */
@Document(indexName = "person", type = "person", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 名字
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 国家
     */
    @Field(type = FieldType.Keyword)
    private String country;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 生日
     */
    @Field(type = FieldType.Date)
    private Date birthday;

    /**
     * 介绍
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String remark;
}