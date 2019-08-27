package com.springboot.db.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.springboot.db.mybatis.mapper") //注入本配置类,springboot执行时候将扫描mapper文件夹，就可以自动装配到要使用的类中
public class MybatisConfig {
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        ConfigurationCustomizer configurationCustomizer = configuration -> {
//            //*注册Map 下划线转驼峰*
//            configuration.setMapUnderscoreToCamelCase(true);
//            configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
//            configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//            configuration.setJdbcTypeForNull(JdbcType.NULL);
//        };
//        return configurationCustomizer;
//    }
}
