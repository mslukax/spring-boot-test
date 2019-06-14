package com.springboot.db.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
//使用jedis访问Redis
public class RedisController {
    @RequestMapping("/redis")
    public String GetRedis(){
        //1.设置IP和端口
        Jedis jedisTest = new Jedis("192.168.111.128",6379);//服务器地址加端口号（注意服务器必须将端口号暴露出来）
        jedisTest.auth("mm88872773"); //输入密码
        //2.保存数据(key-value)
        jedisTest.set("name", "lukax");
        //3.获取数据
        String name = jedisTest.get("name");
        //4.释放资源
        jedisTest.close();
        return name;
    }
}
