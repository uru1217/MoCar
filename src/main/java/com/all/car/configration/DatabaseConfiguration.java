package com.all.car.configration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//@Configuration 애너테이션이 선언 된 java 클래스는 스프링 IoC Container에게 해당 파일이 환경 설정과 관련된 파일(Bean 구성 Class)이라는 것을 인식시킨다. @Bean으로 빈으로 만들었기 때문에 런타임시 스프링에서 싱글톤으로 관리한다.
@Configuration
//스프링 IoC Container에서 런타임시 properties값을 가져오기 위함

@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    //스프링 IoC Container를 사용하기 위한 applicationContext 주입
    private ApplicationContext applicationContext;

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    //스프링 IoC Container에 의해서 만들어진 자바 객체를 스프링 Bean(빈)이라고 부른다
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());
        return dataSource;
    }

    @Bean
    //    DataSource 객체를 받아서 만들어진 setDataSource로 설정 값, setMapperLocations로 mapper 파일 스캔 경로 등 기본을 설정 한 sqlSessionFactory빈이다.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //“classpath:/mapper/*/.xml”는 예를 들어서 mapper 경로를 src/main/resources/mapper 밑으로 /example/example.xml 파일들를 스캔해서 설정하겠다는 의미이다.
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    //MyBatis에서 SqlSession를 이용해 DataSource(데이터베이스 연결정보)로 실제로 DB에 접근하는 빈이다.
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}