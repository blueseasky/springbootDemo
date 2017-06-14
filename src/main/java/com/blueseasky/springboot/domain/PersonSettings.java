package com.blueseasky.springboot.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by renlei on 2017/6/7.
 */
@Component
@ConfigurationProperties(prefix = "person", locations = "classpath:config/person-settings.properties")
public class PersonSettings {

    private String name;
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
