package com.example.demo.config;

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
 * @Description:设置对应数据源扫描的对应的 dao接口层 以及  mapper.xml层 的文件
 * @Author HeSuiJin
 * @Date 2021/7/2
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.modules.mysqlModule.dao", sqlSessionTemplateRef  = "mysqlSqlSessionTemplate")
public class DataSourceMysqlConfig {


    /**
     * 配置SQL会话工厂
     * @param dataSource  Mysql数据源（于DataSourceMysqlProperties文件生成）
     * @return 返回SQL会话工厂
     * @throws Exception 异常
     */
    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/**/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource  Mysql数据源（于DataSourceMysqlProperties文件生成）
     * @return 返回事务管理器
     */
    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置SQL会话模板
     * @param sqlSessionFactory  SQL会话工厂
     * @return 返回SQL会话模板
     * @throws Exception
     */
    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
