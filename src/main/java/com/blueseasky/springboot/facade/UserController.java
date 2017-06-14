package com.blueseasky.springboot.facade;

import com.blueseasky.springboot.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by renlei on 2017/6/9.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping(value = "/")
    public List<User> getUserList(){

        List<User> r = new ArrayList<User>(users.values());

        return r;

    }

    @PostMapping(value = "/", produces = "applicatin/json")
    public String postUser(@ModelAttribute User user){
        System.out.println("接收成功 by @ModelAttribute");
        users.put(user.getId(), user);
        return "success";
    }

    @PostMapping(value = "/body", produces = "application/json")
    public String postUserByBody(@RequestBody User user){

        System.out.println("接收成功 by @RequestBody");
        users.put(user.getId(), user);
        return "success";
    }

    @PostMapping(value = "body/str", produces = "application/json")
    public String postByBody(@RequestBody String str){

        System.out.println("接收成功  by @RequestBody '");


        return "success";

    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id){

        return users.get(id);
    }

    @PutMapping(value = "/{id}")
    public String putUser(@PathVariable Long id,@ModelAttribute User user){

        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);

        return "success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable Long id){
        users.remove(id);
        return "success";
    }




}
