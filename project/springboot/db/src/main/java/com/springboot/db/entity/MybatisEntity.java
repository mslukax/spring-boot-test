package com.springboot.db.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data //lombok免get和set
public class MybatisEntity {
    private Long id;
    private String name;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
    private Integer isDelete;

}
