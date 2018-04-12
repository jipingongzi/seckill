package com.example.seckill.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据源配置类,使用druid
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(@Value("${druid.datasource.primary.url}") String url,
                                 @Value("${druid.datasource.primary.username}") String username,
                                 @Value("${druid.datasource.primary.password}") String password,
                                 @Value("${druid.datasource.primary.initialSize}") int initialSize,
                                 @Value("${druid.datasource.primary.minIdle}") int minIdle,
                                 @Value("${druid.datasource.primary.maxActive}") int maxActive) throws SQLException {
        return getDataSource(url, username, password,initialSize,minIdle,maxActive);
    }

    private DataSource getDataSource(String url, String username, String password,int initialSize,int minIdle,int maxActive) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        setConnectionPool(dataSource);
        return dataSource;
    }
    private void setConnectionPool(DruidDataSource dataSource) throws SQLException {
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setFilters("stat,wall,slf4j");
        dataSource.setUseGlobalDataSourceStat(true);
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql","true");
        properties.setProperty("druid.stat.slowSqlMillis","1000");
        dataSource.setConnectProperties(properties);
    }
}
