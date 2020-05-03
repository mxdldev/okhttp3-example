package com.springboot.example.controller;

import com.springboot.example.bean.ResDTO;
import com.springboot.example.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    //用户登录
    @GetMapping("/login")
    public ResDTO login(@RequestParam("userName") String userName, @RequestParam("passWord") int passWord){

        log.info("login start...");
        log.info("userName:"+userName+"passWord:"+passWord);
        ResDTO resDTO = new ResDTO();
        if("mxdl".equals(userName) && passWord == 123456){
            resDTO.setMsg("login succ");
        }else{
            resDTO.setMsg("login fail");
        }
        return resDTO;
    }

    @PostMapping("/addUser")
    public ResDTO<User> addUser(@RequestBody User user){
        log.info("addUser start...");
        log.info(user.toString());
        return ResDTO.onSucc(user);
    }
}
