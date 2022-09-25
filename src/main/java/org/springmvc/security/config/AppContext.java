package org.springmvc.security.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.sql.DataSource;

@Configuration
@Import(WebMvcConfig.class)
@MapperScan("com.springapp.mvc.board.mapper")

public class AppContext {
    @Bean("data")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mariadb://127.0.0.1:3306/interview_assignment_db?useUnicode=yes&amp;characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("pass");
        dataSource.setDefaultAutoCommit(false);
        return dataSource;

    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        //1 Megabyte = 1,000,000 bytes * 5 (MAX UPLOAD SIZE === 5MB)
        multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        return multipartResolver;
    }

}
