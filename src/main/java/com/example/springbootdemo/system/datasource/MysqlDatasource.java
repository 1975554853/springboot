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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author fly
 * oracle数据源
 */
@Configuration
@MapperScan(basePackages = "com.example.springbootdemo.mapper.mysql"
        ,sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlDatasource {

    /**
     * 获取数据源
     * @return
     */
    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource getDatasource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取连接工厂
     * @param dataSource
     * @return
     */
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("mysql") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/mysql/*.xml"));
        return sqlSessionFactoryBean.getObject();

    }

    /**
     * 事务管理器
     */
    @Bean(name = "mysqlDataSourceTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("mysql") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}