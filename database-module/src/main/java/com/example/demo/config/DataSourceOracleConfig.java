package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description:设置对应数据源扫描的对应的 dao接口层 以及  mapper.xml层 的文件
 * @Author HeSuiJin
 * @Date 2021/7/2
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.modules.oracleModule.dao", sqlSessionTemplateRef  = "oracleSqlSessionTemplate")
public class DataSourceOracleConfig {


    /**
     * 配置SQL会话工厂
     * @param dataSource  oracle数据源（于DataSourceOracleProperties文件生成）
     * @return 返回SQL会话工厂
     * @throws Exception 异常
     */
    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oracle/**/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource  oracle数据源（于DataSourceOracleProperties文件生成）
     * @return 返回事务管理器
     */
    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置SQL会话模板
     * @param sqlSessionFactory  SQL会话工厂
     * @return 返回SQL会话模板
     * @throws Exception
     */
    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
