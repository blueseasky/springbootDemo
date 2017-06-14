package com.blueseasky.springboot.facade;

import com.blueseasky.springboot.domain.PersonSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renlei on 2017/6/7.
 */
@RestController
//@EnableAutoConfiguration
public class WebController{

    @Autowired
    private PersonSettings personSettings;

    @GetMapping(value = "/demo")
    public String demo(){

        return "HelloWorld";
    }

    /*
    * 返回为Object类型，自动转换为json
    * */
    @GetMapping(value = "/person")
    public String person(){

         return personSettings.getName() + " " +personSettings.getAge();
    }

    @GetMapping(value = "/personSettings")
    public PersonSettings personSettings(){

        return personSettings;
    }

    /*
    * 正则匹配
    * */
    @GetMapping(value = "/person{id}")
    public String showId(@PathVariable("id") Integer id){

        return "Id:" + id;
    }



}
