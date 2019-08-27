package com.springboot.db.mybatis;

import com.springboot.db.entity.MybatisEntity;
import com.springboot.db.mybatis.mapper.MybatisTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//资料地址,   https://mp.baomidou.com/
@Service
public class MybatisService {
    @Autowired
    MybatisTestMapper mybatisTestMapper;

    public List<MybatisEntity> GetMybatisData(){
        //List<MybatisEntity> list = mybatisTestMapper.selectList(null); //使用mybatis-plus扩展方法,
        //System.out.println(list);//注意使用该方法时候，因为没有设定表名,所以框架会自动获取mapper接口文件得前缀设定为表名去查询,例如: TestMapper,表为Test，这里MybatisMapper会报错
        List<MybatisEntity> list2 = mybatisTestMapper.getTestData(); //使用mapper接口获取对应xml文件中的sql获取数据
        System.out.println(list2);
        return list2;
    }

    @Transactional //开启事务
    public void Transaction(){
        List<MybatisEntity> list1 = mybatisTestMapper.getTestData();
        System.out.println(list1);
        //List<MybatisEntity> list2 = mybatisTestMapper.getTestData(); //开启事务之后，只能执行一个sql操作,否则报错
        //System.out.println(list2);
        Transaction2(); //事务可以嵌事务
    }

    @Transactional
    public void Transaction2(){
        List<MybatisEntity> list1 = mybatisTestMapper.getTestData();
        System.out.println(list1);
    }

    //搭建Mybatis步骤:
    //1.导入依赖
    //2.创建存放mapper得文件夹，并且在Application中，
    //添加注解 @MapperScan("com.springboot.db.mybatis.mapper")，表示springboot加载后扫描该文件夹并导入转载到容器中。
    //3.mapper中添加接口(Mybatis-plus可继承BaseMapper),添加Mapper.xml文件。
    //4.application.yml中添加配置  mybatis-plus:  mapper-locations: classpath*:/mapper/*Mapper.xml, 用于Mybatis加载某个xml文件。

    //注意：这里对于Mapper文件名字约定特别要注意,Mapper接口文件跟xml文件前缀要一致,最好是表名


//    工作原理解析(重点)
//    mybatis应用程序通过SqlSessionFactoryBuilder从mybatis-config.xml配置文件（也可以用Java文件配置的方式，需要添加@Configuration）中构建出SqlSessionFactory（SqlSessionFactory是线程安全的）；
//    然后，SqlSessionFactory的实例直接开启一个SqlSession，再通过SqlSession实例获得Mapper对象并运行Mapper映射的SQL语句，完成对数据库的CRUD和事务提交，之后关闭SqlSession。
//    说明：1.SqlSession是单线程对象，因为它是非线程安全的，是持久化操作的独享对象，类似jdbc中的Connection，底层就封装了jdbc连接。
//          2.SqlSession中获取Mapper对象，会使用jdk动态代理产生mapper代理对象(jdk动态代理只针对接口，也是为啥mapper要写成接口的原因)，
//          代理类里面会帮我们添加处理mapper.xml的代码(然后实现mapper接口方法)，读取对应mapper.xml文件，解析执行sql。

//    详细流程如下：
//            1、加载mybatis全局配置文件（数据源、mapper映射文件等），解析配置文件，MyBatis基于XML配置文件生成Configuration，和一个个MappedStatement（包括了参数映射配置、动态SQL语句、结果映射配置），其对应着<select | update | delete | insert>标签项。
//            2、SqlSessionFactoryBuilder通过Configuration对象生成SqlSessionFactory，用来开启SqlSession。
//            3、SqlSession对象完成和数据库的交互：
//    a、用户程序调用mybatis接口层api（即Mapper接口中的方法）
//    b、SqlSession通过调用api的Statement ID找到对应的MappedStatement对象
//    c、通过Executor（负责动态SQL的生成和查询缓存的维护）将MappedStatement对象进行解析，sql参数转化、动态sql拼接，生成jdbc Statement对象
//    d、JDBC执行sql。
//    e、借助MappedStatement中的结果映射关系，将返回结果转化成HashMap、JavaBean等存储结构并返回。
}
