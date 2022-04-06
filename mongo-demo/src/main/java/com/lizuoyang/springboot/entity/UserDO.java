package com.lizuoyang.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName UserDO
 * @Description 用户类
 * @Author LiZuoYang
 * @Date 2022/4/6 16:47
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class UserDO {
    @Id
    private String _id;
    private String name;
    private Integer age;
}
