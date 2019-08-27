package com.springboot.db.controller;

import com.springboot.db.entity.MybatisEntity;
import com.springboot.db.mybatis.MybatisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {
    @Autowired
    MybatisService mybatisService;

    @ApiOperation(value="获取数据", notes="获取数据")
    @ApiImplicitParam(name = "id", value = "信息ID", required = true, dataType = "String")
//    @ApiImplicitParams({  //多个参数写法
//            @ApiImplicitParam(name = "id", value = "信息ID", required = true, dataType = "String", paramType = "String"),
//            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "String")
//    })
    @RequestMapping(value = "/GetData", method = RequestMethod.POST)
    //注意：
    // 1.RequestMapping一定要写上请求方式post或get，否则swagger会显示所有请求方式,使用GetMapping或PostMapping也可
    // 2.传多个参数时只用get(@RequestParam)，如果是post就传一个实体(@RequestBody)
    // 3.添加了@RequestParam或@RequestBody参数必须必传
    public List<MybatisEntity> Getmybatis(@RequestParam String id){
        return mybatisService.GetMybatisData();
    }


    @ApiOperation(value="Post获取数据", notes="Post获取数据")
    @PostMapping(value = "/GetData2")
    public List<MybatisEntity> Getmybatis2(@RequestBody @Valid MybatisEntity entity){
        return mybatisService.GetMybatisData();
    }


    //注解解释:
    //1.swagger
//    @Api：修饰整个类，描述Controller的作用
//    @ApiOperation：描述一个类的一个方法，或者说一个接口
//    @ApiParam：单个参数描述
//    @ApiModel：用对象来接收参数
//    @ApiProperty：用对象接收参数时，描述对象的一个字段
//    @ApiResponse：HTTP响应其中1个描述
//    @ApiResponses：HTTP响应整体描述
//    @ApiIgnore：使用该注解忽略这个API
//    @ApiError ：发生错误返回的信息
//    @ApiParamImplicitL：一个请求参数
//    @ApiParamsImplicit 多个请求参数


    //接口请求注解:
//    @Null 限制只能为null
//    @NotNull 限制必须不为null
//    @AssertFalse 限制必须为false
//    @AssertTrue 限制必须为true
//    @DecimalMax(value) 限制必须为一个不大于指定值的数字
//    @DecimalMin(value) 限制必须为一个不小于指定值的数字
//    @Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
//    @Future 限制必须是一个将来的日期
//    @Max(value) 限制必须为一个不大于指定值的数字
//    @Min(value) 限制必须为一个不小于指定值的数字
//    @Past 限制必须是一个过去的日期
//    @Pattern(value) 限制必须符合指定的正则表达式
//    @Size(max,min) 限制字符长度必须在min到max之间
//    @Past 验证注解的元素值（日期类型）比当前时间早
//    @NotEmpty 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
//    @NotBlank 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
//    @Email 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式

    //@RequestMapping 处理请求地址映射(配置映射地址(接口url)和请求方式(post或get等)),可作用于类或者方法。
}
