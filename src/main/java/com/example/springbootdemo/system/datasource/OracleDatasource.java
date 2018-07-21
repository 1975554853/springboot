package com.example.springbootdemo.system.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author fly
 * oracle数据源
 */
@Configuration
@MapperScan(basePackages = "com.example.springbootdemo.mapper.oracle"
        ,sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleDatasource {

    /**
     * 获取数据源
     * @return
     */
    @Bean(name = "oracle")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource getDatasource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取连接工厂
     * @param dataSource
     * @return
     */
    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("oracle") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();

    }

    /**
     * 事务管理器
     */
    @Bean(name = "oracleDataSourceTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("oracle") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}