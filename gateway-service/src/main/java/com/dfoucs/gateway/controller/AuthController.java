package com.dfoucs.gateway.controller;

import com.dfoucs.gateway.auth.UcenterAuth;
import com.dfoucs.gateway.service.impl.UserServiceImpl;
import com.dfoucs.gateway.util.JwtUtils;
import com.dfoucs.gateway.vo.User;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UcenterAuth ucenterAuth;

    @RequestMapping("/jwt")
    public Map<String,Object> authorize(HttpServletRequest request){
        Map<String,Object> map =new HashMap<>();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //User user=userService.login(username,password);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        if (user == null) {
            map.put("msg","用户不存在");
            return map;
        }
        String token ="";
        try{
            Map<String,Object> mapUser =new HashMap<>();
            mapUser.put("username",user.getUsername());
           token = ucenterAuth.getToken(mapUser);
        }catch (Exception e){
            map.put("msg","获取token异常");
            return map;
        }
        map.put("msg","ok");
        map.put("data",token);
        return map;
    }
}
