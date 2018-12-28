package com.example.aop;

import com.example.aop.serivce.testSer;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @Autowired
    private testSer ser;
    @RequestMapping("/a")
    @ResponseBody
    public String a(String name){
        return   ser.a(name);
    }
}
