package com.lizuoyang.springboot.mapper;

import com.lizuoyang.springboot.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description user dao by mongoTemplate
 * @Author LiZuoYang
 * @Date 2022/4/6 17:42
 **/
@Repository
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(UserDO userDO) {
        mongoTemplate.insert(userDO);
    }

    public void updateById(UserDO userDO) {
        // 生成修改条件
        final Update update = new Update();
        // 反射遍历userDo，将非空字段设置到Update中
        ReflectionUtils.doWithFields(userDO.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                // 排除指定条件
                if ("id".equals(field.getName()) // 排除 id 字段，因为作为查询主键
                        || field.getAnnotation(Transient.class) != null // 排除 @Transient 注解的字段，因为非存储字段
                        || Modifier.isStatic(field.getModifiers())) { // 排除静态字段
                    return;
                }
                // 设置字段可反射
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                // 排除字段为空的情况
                if (field.get(userDO) == null) {
                    return;
                }
                // 设置更新条件
                update.set(field.getName(), field.get(userDO));
            }
        });

        // 避免条件为空
        if (update.getUpdateObject().isEmpty()) {
            return;
        }

        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(userDO.get_id())), update, UserDO.class);

    }

    public void deleteById(String id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

    public UserDO findById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

    public UserDO findByUsername(String username) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(username)), UserDO.class);
    }

    public List<UserDO> findAllById(List<String> ids) {
        return mongoTemplate.find(new Query(Criteria.where("_id").in(ids)), UserDO.class);
    }
}
