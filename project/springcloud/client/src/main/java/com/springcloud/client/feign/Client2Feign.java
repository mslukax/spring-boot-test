package com.springcloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client2") //微服务名称
public interface Client2Feign {
    @RequestMapping(value ="/feignTest", method = RequestMethod.GET)
    String feignTest(@RequestParam("name") String name);

//    @GetMapping("/feignTest")
//    String feignTest(String name);
}

//Feign使用步骤
//1.@EnableFeignClients，main方法中添加
//2.定义feign接口类,@FeignClient(name = "client2")，name为服务应用名称
//3.每个接口方法对应服务端中controller的接口名称(controller名称+下面mapper中的接口名称)，
// 请求方式(get[GetMapping] post[PostMapping])，或者@RequestMapping
// 参数以及返回值都要一致(有参数时必须添加后面两个注解),单个参数 @RequestParam("name")，实体参数 @RequestBody Entity entity
//4.@Autowired直接自动转配到需要调用的类中，例如 Client2Feign.feignTest();


//Feign工作原理
//当程序启动时，回进行包扫描，扫描所有@FeignClients的注解的类，并且讲这些信息注入Spring IOC容器中，
//当定义的的Feign接口中的方法呗调用时，通过JDK的代理方式，来生成具体的RequestTemplate.当生成代理时，
//Feign会为每个接口方法创建一个RequestTemplate。当生成代理时，Feign会为每个接口方法创建一个RequestTemplate对象，
//改对象封装可HTTP请求需要的全部信息，如请求参数名，请求方法等信息都是在这个过程中确定的。
//然后RequestTemplate生成Request,然后把Request交给Client去处理，这里指的时Client可以时JDK原生的URLConnection,Apache的HttpClient,
//也可以时OKhttp，最后Client被封装到LoadBalanceClient类，这个类结合Ribbon负载均衡发器服务之间的调用。


//Ribbon工作原理(客户端负载均衡器，使用Feign和RestTemplete[@LoadBalanced]都会用到)
//Ribbon是一个基于HTTP和TCP的客户端负载均衡器，当我们将Ribbon和Eureka一起使用时，Ribbon会从Eureka注册中心去获取服务端列表
//由于所有微服务都是在Eureka中注册完成并且调用，包括：同一个服务被发布在多个服务器上等这种情况。
//因此Ribbon是利用了这一点，可以根据服务名称(重要)，在Eureka中寻找对应的服务名称的所有分布式服务，就可以
//找到对应的分布式服务，这时就可以使用负载均衡策略，获取其中的一个分布式服务进行访问。

//注意：使用的时候，千万记住，现在所有的服务名字一定要保持一致，如果不一致则会认为是两个服务，无法进行负载均衡。


//微服务分布式操作：
//必须保证发布的分布式服务名称都是一致，都必须注册到Eureka，至于发布在什么服务器，什么端口，没有任何关系。
