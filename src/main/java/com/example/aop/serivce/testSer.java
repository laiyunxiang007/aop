package com.example.aop.serivce;

import org.springframework.stereotype.Service;

@Service
public class testSer {

    public String a(String name){
        return "你的名字:"+name;
    }
}
